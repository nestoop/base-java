package com.sohouer.core.kafka.serilizer;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.nustaq.serialization.FSTConfiguration;

public class KafkaModelDeSerilizer implements Deserializer<Object>{
	
	public static FSTConfiguration configuration = FSTConfiguration.createStructConfiguration();

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// nothing to do
		
	}

	@Override
	public Object deserialize(String topic, byte[] data) {
		
		return configuration.asObject(data);
	}

	@Override
	public void close() {
		// nothing to do
		
	}


}
