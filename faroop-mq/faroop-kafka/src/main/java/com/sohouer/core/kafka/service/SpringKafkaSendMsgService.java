package com.sohouer.core.kafka.service;

import com.sohouer.core.kafka.Device;

public interface SpringKafkaSendMsgService {
	
	public void sendMsg(String topic,int key, Device data);


}
