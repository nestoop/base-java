package com.sohouer.core.kafka.callback;

import java.util.Map;

import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Component;

@Component
public class KafkaOffsetCommitCallback implements  OffsetCommitCallback {

	@Override
	public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets,
			Exception exception) {
	}

}
