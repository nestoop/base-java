package com.sohouer.core.kafka.message.dispatch;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface SpringKafkaMessageDispatch {
	
	public void messageDispatch(ConsumerRecord<String, Object> record, List<String> topics);

}
