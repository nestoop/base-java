package com.sohouer.core.kafka.runner;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.sohouer.core.kafka.consumer.config.SpringKafkaConsumerConfig;
import com.sohouer.core.kafka.message.dispatch.SpringKafkaMessageDispatch;


@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpringKafkaConsumerRunner implements Runnable{
	
	@Resource
	private SpringKafkaMessageDispatch messageDispatch;
	
	//创建等待队列
	private static final BlockingQueue<Runnable> bqueue = new ArrayBlockingQueue<Runnable>(20);  
	
	//创建线程池
	private  static final ThreadPoolExecutor pool = new ThreadPoolExecutor(2,3,2,TimeUnit.MILLISECONDS,bqueue);
	
	private List<String> topics;
	
	private String groupid;
	
	public SpringKafkaConsumerRunner( List<String> topics,
			String groupid) {
		this.topics = topics;
		this.groupid = groupid;
	}

	public SpringKafkaConsumerRunner() {}

	
	
	

	@Override
	public void run() {
		
		 KafkaConsumer<String, Object> consumer = new KafkaConsumer<String, Object>(SpringKafkaConsumerConfig.getConsumerProperties(groupid,false));
	     consumer.subscribe(topics);
	     try {
	         while(true) {
	             ConsumerRecords<String, Object> records = consumer.poll(100);
	             for (TopicPartition partition : records.partitions()) {
	                 List<ConsumerRecord<String, Object>> partitionRecords = records.records(partition);
	                 for (ConsumerRecord<String, Object> record : partitionRecords) {
	                	 System.out.println(messageDispatch ==null);
	                	 System.out.println(topics ==null);
	                	 System.out.println(record ==null);
	                	 messageDispatch.messageDispatch(record,topics);
	                 }
	                 long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
	                 consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
	             }
	         }
	     } finally {
	       consumer.close();
	     }
	}
	
	/**
	 * 获取消息
	 * @param topics
	 * @param groupid
	 */
	public void pollMessage(List<String> topics,String groupid){
		
		SpringKafkaConsumerRunner consumerRunner = new SpringKafkaConsumerRunner(topics, groupid);
		pool.execute(consumerRunner);
	}
	

}
