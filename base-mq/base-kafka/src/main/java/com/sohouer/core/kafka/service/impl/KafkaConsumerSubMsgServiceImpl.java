package com.sohouer.core.kafka.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Service;

import com.sohouer.core.kafka.Device;
import com.sohouer.core.kafka.consumer.config.SpringKafkaConsumerConfig;
import com.sohouer.core.kafka.message.dispatch.SpringKafkaMessageDispatch;
import com.sohouer.core.kafka.service.KafkaConsumerSubMsgService;

@Service
public class KafkaConsumerSubMsgServiceImpl implements KafkaConsumerSubMsgService {
	
	@Resource
	private SpringKafkaMessageDispatch messageDispatch;

	@Override
	public void pullMessage(List<String> topics, String groupid) {
	    KafkaConsumer<String, Object> consumer = new KafkaConsumer<String, Object>(SpringKafkaConsumerConfig.getConsumerProperties(groupid,false));
	     consumer.subscribe(topics);
	     try {
	         while(true) {
	             ConsumerRecords<String, Object> records = consumer.poll(100);
	             for (TopicPartition partition : records.partitions()) {
	                 List<ConsumerRecord<String, Object>> partitionRecords = records.records(partition);
	                 for (ConsumerRecord<String, Object> record : partitionRecords) {
	                	 messageDispatch.messageDispatch(record, topics);
	                 }
	                 long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
	                 consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
	             }
	         }
	     } finally {
	       consumer.close();
	     }
	}

	@Override
	public void pullMessageByPartition(List<String> topics, String groupid,int partitionNumer) {
			
			List<TopicPartition> TopicPartitions= new ArrayList<TopicPartition>();
			for(String topic:topics){
				for(int i=0 ;i<partitionNumer;i++){
					TopicPartition partition = new TopicPartition(topic, i);
					TopicPartitions.add(partition);
				}
			}
			
			KafkaConsumer<String, Object> consumer = new KafkaConsumer<String, Object>(SpringKafkaConsumerConfig.getConsumerProperties(groupid,false));
			consumer.assign(TopicPartitions);
			try {
				while(true) {
					ConsumerRecords<String, Object> records = consumer.poll(100);
					for (TopicPartition partition : records.partitions()) {
						List<ConsumerRecord<String, Object>> partitionRecords = records.records(partition);
						for (ConsumerRecord<String, Object> record : partitionRecords) {
							Device device = (Device) record.value();
							System.out.println("----------pullMessageByPartition---获取订阅消息 开始----------------------------");
							System.out.println(record.offset() + "---device devicenumber: " + device.getNumber());
							System.out.println(record.offset() + "---device groupid: " + device.getGroupId());
							System.out.println(record.offset() + " ---key: " + record.key());
							System.out.println(record.offset() + "--groupid: " + groupid);
							System.out.println(record.offset() + "--topic: " + record.topic());
							System.out.println("---------pullMessageByPartition-------获取订阅消息 结束----------------------------");
						}
						long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
						consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
					}
				}
			} finally {
				consumer.close();
			}
			
	}

}
