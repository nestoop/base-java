package com.sohouer.core.kafka.consumer;

import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.sohouer.core.kafka.consumer.config.SpringKafkaConsumerConfig;

public class SpringKafkaConsumerHandler {
	
	private   KafkaConsumer<String,Object> kafkaConsumer;
	
	private final List<String> topics;
	
	public SpringKafkaConsumerHandler(List<String> topics,String  groupid) {
		this.kafkaConsumer = new KafkaConsumer<String, Object>(SpringKafkaConsumerConfig.getConsumerProperties(groupid,false));
		this.topics = topics;
	}
	
	public SpringKafkaConsumerHandler setConsumerProperties(Properties properties){
		this.kafkaConsumer =  new KafkaConsumer<String, Object>(properties);
		return this;
	}


	public ConsumerRecords<String,Object> pollMessage() {
		try{
			//订阅主题
			kafkaConsumer.subscribe(topics);
			//拉取消息
			ConsumerRecords<String,Object> consumerRecords= kafkaConsumer.poll(100);
			
			return consumerRecords;
			
		}catch(Exception e){
			close();
			
		}finally{
			close();
			commitSync();
		}
		return null;
		
	}
	public ConsumerRecords<String,Object> pollMessageASync() {
		try{
			//订阅主题
			kafkaConsumer.subscribe(topics);
			//拉取消息
			ConsumerRecords<String,Object> consumerRecords= kafkaConsumer.poll(100);
			
			return consumerRecords;
			
		}catch(Exception e){
			close();
			
		}finally{
			close();
			commitASync();
		}
		return null;
		
	}
	/**
	 * 
	 *close
	 */
	
	public void close(){
		
		kafkaConsumer.close();
	}
	/**
	 * 提交
	 */
	public void commitSync(){
		try {
			kafkaConsumer.commitSync();
		} catch (CommitFailedException e) {
			System.out.println("自動提交異常...................");
		}
	}
	/**
	 * 提交
	 */
	public void commitASync(){
		try {
			kafkaConsumer.commitAsync();
		} catch (CommitFailedException e) {
			System.out.println("自動提交異常...................");
		}
	}
	
	
	
	

}
