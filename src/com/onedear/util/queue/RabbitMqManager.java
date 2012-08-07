package com.onedear.util.queue;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class RabbitMqManager {
	
	private ConnectionFactory factory;
	
	private Channel channel;
	
	private Connection con;
	
	private String exchange ;
	
	private String routingKey ; 
	
	public RabbitMqManager(String host , int port  , String exchange) {
		factory =new com.rabbitmq.client.ConnectionFactory();
		factory.setHost(host);
		factory.setPort(port);
		try {
			con = factory.newConnection();
			channel = con.createChannel();
			channel.exchangeDeclare(exchange, "direct", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setExchange(String exchange){
		this.exchange = exchange;
	}
	
	public void setRountingKey(String routingKey) {
		this.routingKey = routingKey ;
	}
	
	public boolean isActive() {
		return channel != null && channel.isOpen() ; 
	}
	
	public void init() throws IOException {
		this.con = factory.newConnection();
		this.channel = this.con.createChannel();

	}
	
	public void destroy() {
    	if(channel != null) {
    		try {
    			channel.close();
    		} catch(Exception e) {}
    	}
    	if(channel != null) {
    		try {
    			channel.close();
    		} catch(Exception e) {}
    	}
	}
	
	public void publish(byte[] bytes) throws IOException {
		channel.basicPublish(exchange, routingKey
				, MessageProperties.MINIMAL_PERSISTENT_BASIC, bytes);
	}	
	
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
//		mq.host=192.168.0.186
//				mq.port=5673
//				mq.exchange=7days
//				mq.rountingKey=7daysRountingKey
		RabbitMqManager rr = new RabbitMqManager("192.168.0.186",5673 , "7days" );
		rr.publish("xxx".getBytes("utf-8"));
	}
	
}
