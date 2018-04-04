package com.sohouer.core.kafka.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Service;

import com.sohouer.core.kafka.Device;
import com.sohouer.core.kafka.serilizer.KafkaModelDeSerilizer;

@Service
public class SpringKafkaConsumerThread implements Runnable {
	
	public static final ExecutorService executor = Executors.newFixedThreadPool(10);

	private SpringKafkaConsumerHandler handler;
	
	private String groupid;

	public List<String> subscribeTopics = new ArrayList<String>();

	public SpringKafkaConsumerThread(int i, String groupid, List<String> subscribeTopics) {
		this.subscribeTopics = subscribeTopics;
        this.groupid = groupid;
		handler = new SpringKafkaConsumerHandler(subscribeTopics,groupid);
	}
	



	public SpringKafkaConsumerThread() {
	}




	@Override
	public void run() {

		try {
//			while (true) {
				ConsumerRecords<String, Object> records = handler.pollMessage();
				for (ConsumerRecord<String, Object> record : records){
					Device device = (Device) record.value();
					System.out.println("-------------获取订阅消息 开始----------------------------");
					System.out.println(record.offset() + "---device devicenumber: " + device.getNumber());
					System.out.println(record.offset() + "---device groupid: " + device.getGroupId());
					System.out.println(record.offset() + " ---key: " + record.key());
					System.out.println(record.offset() + "--groupid: " + groupid);
					System.out.println(record.offset() + "--topic: " + record.topic());
					System.out.println("----------------获取订阅消息 结束----------------------------");
					try {
						handler.commitASync();
					} catch (CommitFailedException e) {
					}
//				}
			}
		} finally {
			handler.close();
		}

	}
	
	public void pullMessage(List<String> topics) {
		int numConsumers =10;
		 final List<SpringKafkaConsumerThread> consumers = new ArrayList<SpringKafkaConsumerThread>();
		 for (int i = 0; i < numConsumers; i++) {
			 //groupid
			String uuid = UUID.randomUUID().toString();
			//創建消費者線程
		    SpringKafkaConsumerThread consumer = new SpringKafkaConsumerThread(i, "inspiry-"+uuid,topics);
		    consumers.add(consumer);
		    executor.submit(consumer);
		 }
	}
	
	public void pullMessage(List<String> topics,String groupid) {
		int numConsumers =1;
		final List<SpringKafkaConsumerThread> consumers = new ArrayList<SpringKafkaConsumerThread>();
		for (int i = 0; i < numConsumers; i++) {
			//創建消費者線程
			SpringKafkaConsumerThread consumer = new SpringKafkaConsumerThread(i, groupid,topics);
			consumers.add(consumer);
			executor.submit(consumer);
		}
	}



}
