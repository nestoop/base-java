package com.sohouer.core.kafka.message.dispatch.impl;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sohouer.core.kafka.Device;
import com.sohouer.core.kafka.message.dispatch.SpringKafkaMessageDispatch;


@Component
public class SpringKafkaMessageDispatchImpl implements SpringKafkaMessageDispatch {


	@Override
	public void messageDispatch(ConsumerRecord<String, Object> record,List<String> topics) {
		
		Device device = (Device) record.value();
		System.out.println("-----messageDispatch--------获取订阅消息 开始----------------------------");
		System.out.println(record.offset() + "---device devicenumber: " + device.getNumber());
		System.out.println(record.offset() + "---device groupid: " + device.getGroupId());
		System.out.println(record.offset() + " ---key: " + record.key());
		System.out.println(record.offset() + "--topic: " + record.topic());
		System.out.println("------messageDispatch----------获取订阅消息 结束----------------------------");
		
		
		
	}

}
