package com.sohouer.core.kafka.consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;


/**
 * 实现根据主题获取消息，不根据消费组
 * @author Administrator
 *
 */
@Component
public class SpringKafkaConsumer {

	@Resource
	private SpringKafkaConsumerThread springKafkaConsumerThread ;
	

	public void pullMessage(List<String> topics) {
		springKafkaConsumerThread.pullMessage(topics);
	}
	
	public void pullMessage(List<String> topics,String groupid) {
		springKafkaConsumerThread.pullMessage(topics,groupid);
	}


	

}
