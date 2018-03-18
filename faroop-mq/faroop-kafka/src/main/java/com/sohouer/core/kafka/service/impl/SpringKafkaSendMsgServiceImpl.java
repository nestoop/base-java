package com.sohouer.core.kafka.service.impl;



import javax.annotation.Resource;

import org.springframework.integration.kafka.support.KafkaHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.sohouer.core.kafka.Device;
import com.sohouer.core.kafka.service.SpringKafkaSendMsgService;


@Service
public class SpringKafkaSendMsgServiceImpl implements SpringKafkaSendMsgService{
	
	
	@Resource
	private KafkaTemplate<Integer, Device> kafkaTemplate;

	@Override
	public void sendMsg(String topic,int key, Device data) {
		
		for(int  i= 0;i<1;i++){
			Message<Device> message = MessageBuilder.withPayload(data)
					.setHeader(KafkaHeaders.TOPIC, topic)
//					.setHeader(KafkaHeaders.PARTITION_ID, 1)
					.setHeader(KafkaHeaders.MESSAGE_KEY, 0).build();
			
//			ListenableFuture<SendResult<Integer, Device>> future =kafkaTemplate.send(topic,i, key, data);
			ListenableFuture<SendResult<Integer, Device>> future =kafkaTemplate.send(message);
			future.addCallback(new ListenableFutureCallback<SendResult<Integer, Device>>(){
				
				@Override
				public void onSuccess(SendResult<Integer, Device> result) {
					
					System.out.println("----------------------------send message---------------------------------------------------------------");
					System.out.println("发送消息成功 主题-topic:"+result.getRecordMetadata().topic()+" 块：---partion:"+result.getRecordMetadata().partition());
					System.out.println("-send message device number GroupId-"+result.getProducerRecord().value().getGroupId());
					System.out.println("-send message device number number-"+result.getProducerRecord().value().getNumber());
					System.out.println("-----------------------------send message---------------------------------------------------------------");
				}
				
				@Override
				public void onFailure(Throwable ex) {
					System.out.println("send fail exception:"+ex.getMessage());
				}
				
			});
		}
	}
	

}
