package com.sohouer.core.kafka.listener;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer.AckMode;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Component;

import com.sohouer.core.kafka.Device;


@Component
public  class SpringKafkaMessageListenerContainer {
	
	@Resource
	private ConsumerFactory<Object, Device> consumerFactory;
	
	public void  listenerContainer(String topic){
		
		KafkaMessageListenerContainer<Object, Device> container =
                new KafkaMessageListenerContainer<Object, Device>(consumerFactory, topic);
		container.setMessageListener(new SpringKafkaAcknowledgingMessageListener());
		container.setAckMode(AckMode.MANUAL_IMMEDIATE);
		container.start();
	}
	


	public static class SpringKafkaListener implements MessageListener<Object, Device> {
	
		@Override
		public void onMessage(ConsumerRecord<Object, Device> record) {
			System.out.println("------------------------------MessageListener-------------------------------------------------------------");
			Device message= record.value();
			System.out.println("----MessageListener-----------接收的topic："+record.topic());
			System.out.println("----MessageListener-----------接收的message device group id："+message.getGroupId());
			System.out.println("----MessageListener-----------接收的message device deviceNumber："+message.getNumber());
			System.out.println("----MessageListener-----------接收的partition："+record.partition());
			System.out.println("----MessageListener-----------接收的key："+record.key());
			System.out.println("--------------------------------MessageListener-----------------------------------------------------------");
			
		}
	
	}
	/**
	 * 提交消费提醒
	 * 
	 * @author xbao
	 *
	 */
	public static class SpringKafkaAcknowledgingMessageListener  implements AcknowledgingMessageListener<Object, Device>{

		@Override
		public void onMessage(ConsumerRecord<Object, Device> record,
				org.springframework.kafka.support.Acknowledgment acknowledgment) {
			System.out.println("------------------------------AcknowledgingMessageListener------start-------------------------------------------------------");
			Device message= record.value();
			System.out.println("----AcknowledgingMessageListener-----------接收的topic："+record.topic());
			System.out.println("----AcknowledgingMessageListener-----------接收的message device group id："+message.getGroupId());
			System.out.println("----AcknowledgingMessageListener-----------接收的message device deviceNumber："+message.getNumber());
			System.out.println("----AcknowledgingMessageListener-----------接收的partition："+record.partition());
			System.out.println("----AcknowledgingMessageListener-----------接收的key："+record.key());
			System.out.println("--------------------------------AcknowledgingMessageListener-----end------------------------------------------------------");
			acknowledgment.acknowledge();
			
		}
		
		
	}
	

}
