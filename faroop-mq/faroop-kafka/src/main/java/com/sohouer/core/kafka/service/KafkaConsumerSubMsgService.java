package com.sohouer.core.kafka.service;

import java.util.List;

/**
 * 订阅消息
 * @author Administrator
 *
 */
public interface KafkaConsumerSubMsgService {
	
	/**
	 * 同一个消费组中的消费消息
	 * @param topics
	 * @param groupid
	 */
	public  void pullMessage(final List<String> topics,final String groupid);
	/**
	 * 
	 * 同一个消费组中的消费消息,有块 编号
	 * @param topics
	 * @param groupid
	 * @param partitionNumer
	 */
	public  void pullMessageByPartition(final List<String> topics,final String groupid,int partitionNumer);

}
