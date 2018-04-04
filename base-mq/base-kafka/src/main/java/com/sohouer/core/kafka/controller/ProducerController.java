package com.sohouer.core.kafka.controller;

import java.util.Arrays;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sohouer.core.kafka.Device;
import com.sohouer.core.kafka.runner.SpringKafkaConsumerRunner;
import com.sohouer.core.kafka.service.SpringKafkaReviceMsgService;
import com.sohouer.core.kafka.service.SpringKafkaSendMsgService;

@RestController
public class ProducerController {
	
	@Resource
	private SpringKafkaSendMsgService springKafkaSendMsgService;
	
	@Resource
	private SpringKafkaReviceMsgService springKafkaReviceMsgService;
	
	@Resource
	private SpringKafkaConsumerRunner springKafkaConsumerRunner;
	
	@RequestMapping(method=RequestMethod.GET,value="/sm")
	public String sendMsg(){
		for(int i=0;i<1;i++){
			String uuid =UUID.randomUUID().toString();
			Device device = new Device();
			device.setGroupId(uuid);
			device.setNumber("14FF00FF03444"+i);
			springKafkaSendMsgService.sendMsg("inspiryTopic", i, device);
			
		}
		return "ok";
	}
	
	
	

	@RequestMapping(value="/rm", method = RequestMethod.GET)
	public String reviceMsg(){
		while(true){
			
			springKafkaReviceMsgService.ReviceMsg("inspiryTopic");
		}
//		return "ok";
	}
	
	@RequestMapping(value="/rm1", method = RequestMethod.GET)
	public String reviceMsg1(){
//		while(true){
			
			springKafkaReviceMsgService.reviceMsgBygroupid("inspriyy-vv-test",new String[]{"inspiryTopic"});
//		}
		return "ok";
	}
	@RequestMapping(value="/rm2", method = RequestMethod.GET)
	public String reviceMsg4(){
			
		springKafkaConsumerRunner.pollMessage(Arrays.asList("inspiryTopic"), "inspriyy-vv-test");
		return "ok";
	}

}
