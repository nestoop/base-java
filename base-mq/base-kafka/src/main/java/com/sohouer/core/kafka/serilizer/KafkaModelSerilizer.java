package com.sohouer.core.kafka.serilizer;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.nustaq.serialization.FSTConfiguration;


public class KafkaModelSerilizer<Object> implements Serializer<Object>{
	
	public static FSTConfiguration configuration = FSTConfiguration.createStructConfiguration();

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// nothing to do
		
	}

	@Override
	public byte[] serialize(String topic, Object data) {
		if (data == null) {
			return null;
		}
		return configuration.asByteArray(data);
	}

	@Override
	public void close() {
		// nothing to do
		
	}


}
