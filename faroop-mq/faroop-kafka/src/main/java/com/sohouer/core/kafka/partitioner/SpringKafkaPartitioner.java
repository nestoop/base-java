package com.sohouer.core.kafka.partitioner;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;



/**
 * kafka 发送消息分区Partitioner 
 * @author Administrator
 *
 */

public class SpringKafkaPartitioner implements Partitioner{
	
	 private final AtomicInteger counter = new AtomicInteger(new Random().nextInt(10));


	@Override
	public void configure(Map<String, ?> configs) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		 List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
	        int numPartitions = partitions.size();
	        if (keyBytes == null) {
	            int nextValue = counter.getAndIncrement();
	            List<PartitionInfo> availablePartitions = cluster.availablePartitionsForTopic(topic);
	            if (availablePartitions.size() > 0) {
	                int part = SpringKafkaPartitioner.toPositive(nextValue) % availablePartitions.size();
	                return availablePartitions.get(part).partition();
	            } else {
	                // no partitions are available, give a non-available partition
	                return SpringKafkaPartitioner.toPositive(nextValue) % numPartitions;
	            }
	        } else {
	            // hash the keyBytes to choose a partition
	            return SpringKafkaPartitioner.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
	        }
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	private static int toPositive(int number) {
        return number & 0x7fffffff;
    }


}
