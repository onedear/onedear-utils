/**
 * 
 */
package com.onedear.util.queue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * @author kevin
 *
 */
public class ProducerManager {
	
	
	private String rabbitHost;
	private Integer rabbitPort;
	
	private Connection rabbitConnection = null;
	private Channel rabbitChannel = null;

	private List<String> exchanges;
	
	private ReentrantReadWriteLock reInitLock = new ReentrantReadWriteLock();
	
	public ProducerManager(String host, Integer port)  throws IOException {
		this(host, port, new ArrayList<String>(0));
	}
	
	public ProducerManager(String host, Integer port, String exchange) {
		this(host, port, Arrays.asList(exchange));
	}
	
	public ProducerManager(String host, Integer port, List<String> exchanges) {
		this.rabbitHost = host;
		this.rabbitPort = port;
		this.exchanges = exchanges;
	}
	
	public void publish(String severity, String message) throws IOException {
		
		reInitLock.readLock().lock();
		
		try {
			
			for (String exchange : this.exchanges) {
				byte[] messageBytes = message.getBytes("UTF-8");
				rabbitChannel.basicPublish(exchange, severity, MessageProperties.MINIMAL_PERSISTENT_BASIC, messageBytes);
			}
			
		} catch(Exception e) {

			reInitLock.readLock().unlock();
			reInitLock.writeLock().lock();

			try {
				
				destroy();
				init();
				
			} finally {

				reInitLock.readLock().lock();
				reInitLock.writeLock().unlock();
				
			}
			
			
		} finally {
			reInitLock.readLock().unlock();
		}
	}
	
	public void destroy() {
    	if(rabbitChannel != null) {
    		try {
    			rabbitChannel.close();
    		} catch(Exception e) {
    		}
    	}
    	if(rabbitConnection != null) {
    		try {
    			rabbitConnection.close();
    		} catch(Exception e) {
    		}
    	}
	}
	
	public void init() {

		ConnectionFactory factory = new ConnectionFactory();
		
		factory.setHost(this.rabbitHost);
		
		if(this.rabbitPort != null)
			factory.setPort(this.rabbitPort);
		
		try {
			
			this.rabbitConnection = factory.newConnection();

			this.rabbitChannel = this.rabbitConnection.createChannel();
	
			for (String exchange : this.exchanges) {
				this.rabbitChannel.exchangeDeclare(exchange, "direct", true);
			}
		
		} catch (IOException e) {
		}
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		destroy();
	}
	
	public static void main(String[] args) throws IOException {
		
		ProducerManager pm = new ProducerManager("192.168.0.186",5673 , "7days" );
		pm.publish("img", "messageContent");
	}
	
	
}
