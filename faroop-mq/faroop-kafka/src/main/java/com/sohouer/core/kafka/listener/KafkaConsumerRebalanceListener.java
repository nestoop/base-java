package com.sohouer.core.kafka.listener;

import java.util.Collection;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class KafkaConsumerRebalanceListener implements ConsumerRebalanceListener {
	
	private KafkaConsumer<?,?> consumer;

    public KafkaConsumerRebalanceListener(KafkaConsumer<?,?> consumer) {
        this.consumer = consumer;
    }

	@Override
	public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
		for(TopicPartition partition: partitions)
            saveOffsetInExternalStore(consumer.position(partition));
	}

	private void saveOffsetInExternalStore(long position) {
		
	}

	@Override
	public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
		  for(TopicPartition partition: partitions)
              consumer.seek(partition, readOffsetFromExternalStore(partition));
	}

	private long readOffsetFromExternalStore(TopicPartition partition) {
		return 0;
	}

}
