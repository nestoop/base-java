package com.sohouer.core.kafka.consumer.config;

import java.util.Properties;

import org.apache.kafka.common.serialization.StringDeserializer;

import com.sohouer.core.kafka.serilizer.KafkaModelDeSerilizer;

public class SpringKafkaConsumerConfig {
	
	public static Properties getConsumerProperties(String groupid,boolean automit){
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.44.13:9092,192.168.44.14:9092,192.168.44.15:9092");
		props.put("group.id", groupid);
		props.put("enable.auto.commit", automit);
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", KafkaModelDeSerilizer.class.getName());
		return props;
	}
}
