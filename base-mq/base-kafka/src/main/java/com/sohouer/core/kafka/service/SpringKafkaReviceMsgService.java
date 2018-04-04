package com.sohouer.core.kafka.service;

public interface SpringKafkaReviceMsgService {
	
public void ReviceMsg(String...strings);
	
	public void reviceMsgBygroupid(String groupid ,String...strings);


}
