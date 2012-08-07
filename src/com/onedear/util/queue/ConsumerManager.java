/**
 * 
 */
package com.onedear.util.queue;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.QueueingConsumer;

/**
 * @author kevin
 *
 */
public final class ConsumerManager {

	Channel channel = null ; 
	static String queueName = "onload_7days_test";
	//是否持久化
	static boolean durable = true ;
	static String serverity = "onload";
	//是否独占
	static boolean exclusive = false;
	static boolean autoDelete = true ;
	static String rountingKey = "onload";
	
	public ConnectionFactory getCollectionFactory (String host , int port , String exchange) throws IOException {
		ConnectionFactory factory = new ConnectionFactory();
		
		factory.setHost(host);
		
		factory.setPort(port);
		
		return factory;
	}
	
	private String rabbitmqExchange;

	private int rabbitmqQos;
	
	
	private ConnectionFactory rabbitmqFactory;
	
	private final static ExecutorService CONSUMER_THREADS = new ThreadPoolExecutor(4, Integer.MAX_VALUE, 0, TimeUnit.MILLISECONDS,
			new SynchronousQueue<Runnable>(), new ConsumerThreadFactory());
	

	private static class ConsumerThreadFactory implements ThreadFactory {
				
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setDaemon(false);
			return t;
		}
		
	}
	
	private static class ConsumerRunnable implements Runnable {

		
		private Consumer consumer;
		private String consumerName;

		private String rabbitmqExchange;
		private int rabbitmqQos;
		private ConnectionFactory rabbitmqFactory;
		private Connection rabbitmqConnection = null;
		private Channel rabbitmqChannel = null;
		private QueueingConsumer rabbitmqConsumer = null;

		private long retryInterval = 10000;

		
		public ConsumerRunnable(String rabbitmqExchange, int rabbitmqQos, ConnectionFactory rabbitmqFactory) {
			this.rabbitmqExchange = rabbitmqExchange;
			this.rabbitmqQos = rabbitmqQos;
			this.rabbitmqFactory = rabbitmqFactory;
			this.consumerName = this.rabbitmqExchange + '-' + queueName;
		}

		@Override
		public void run() {
			
			while(true) {
				
				prepareRabbitmq();
				
				try {
			        while (true) {
			            QueueingConsumer.Delivery delivery = this.rabbitmqConsumer.nextDelivery();
			            String message = new String(delivery.getBody(), "UTF-8");
			            Thread.sleep(1000);
			            System.out.println(message);
			            this.rabbitmqChannel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			        }
				} catch(Exception e) {
				}
			}
		}

		private void prepareRabbitmq() {
			
			closeRabbitmqConnection();

			while(true) {
				try {

					this.rabbitmqConnection = this.rabbitmqFactory.newConnection();
					this.rabbitmqChannel = this.rabbitmqConnection.createChannel();
					
					this.rabbitmqChannel.exchangeDeclare(this.rabbitmqExchange, "direct", true);
					
					this.rabbitmqChannel.queueDeclare(this.consumerName, durable, exclusive, autoDelete, null);
					this.rabbitmqChannel.queueBind(this.consumerName, this.rabbitmqExchange, rountingKey);
			
					this.rabbitmqConsumer = new QueueingConsumer(this.rabbitmqChannel);
					this.rabbitmqChannel.basicConsume(this.consumerName, false, this.rabbitmqConsumer);

					this.rabbitmqChannel.basicQos(this.rabbitmqQos);
					
					retryInterval = 10000;
					
					break;
					
				} catch(Exception e) {
					
					closeRabbitmqConnection();
					
					try {
						Thread.sleep(retryInterval);
					} catch (InterruptedException ie) { }
					retryInterval += 5000;
				}
			}
		}
		
		private void closeRabbitmqConnection() {
			if(this.rabbitmqChannel != null)
				try {
					this.rabbitmqChannel.close();
				} catch(Exception e) {}
				
			if(this.rabbitmqConnection != null)
				try {
					this.rabbitmqConnection.close();
				} catch(Exception e) {}
		}
		
		public String getConsumerName() {
			return consumerName;
		}
	}
	
	public static void main(String[] args) throws IOException {
		ConsumerManager pm = new ConsumerManager();
		ConnectionFactory cf = pm.getCollectionFactory("192.168.0.186",5673 , "7days");
		ConsumerRunnable cr = new ConsumerRunnable("7days",1024, cf);
		new Thread(cr).start();
	}
	
}
