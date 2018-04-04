package com.sohouer.core.kafka.service.impl;




import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.stereotype.Component;

import com.sohouer.core.kafka.Device;
import com.sohouer.core.kafka.consumer.SpringKafkaConsumer;
import com.sohouer.core.kafka.serilizer.KafkaModelDeSerilizer;
import com.sohouer.core.kafka.service.KafkaConsumerSubMsgService;
import com.sohouer.core.kafka.service.SpringKafkaReviceMsgService;




@Component
public class SpringKafkaReviceMsgServiceImpl implements SpringKafkaReviceMsgService {

	@Resource
	private SpringKafkaConsumer springKafkaConsumer;
	
	@Resource
	private KafkaConsumerSubMsgService kafkaConsumerSubMsgService;
	

	@Override
	public void ReviceMsg(String...strings) {
	
		List<String> topics = Arrays.asList(strings);
		springKafkaConsumer.pullMessage(topics);
		
	}


	@Override
	public void reviceMsgBygroupid(String groupid,String[] strings) {
		List<String> topics = Arrays.asList(strings);
		kafkaConsumerSubMsgService.pullMessage(topics, groupid);
	}
	
	public void reviceMsgByTopicPartition(String groupid,String[] strings,int partition) {
		List<String> topics = Arrays.asList(strings);
		kafkaConsumerSubMsgService.pullMessageByPartition(topics, groupid, partition);
	}
	
	

}
