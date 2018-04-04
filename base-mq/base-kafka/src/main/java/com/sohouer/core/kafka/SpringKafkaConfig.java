package com.sohouer.core.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.SimpleKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.stereotype.Component;


@Configuration
@Component(value = "springKafkaConfig")
@PropertySource(value = { "classpath:kafka.properties" })
public class SpringKafkaConfig {
	
	@Value(value = "${kafka.bootstrap.servers}")
	private String brokers;
	
	@Value(value="${kafka.consumer.group.id}")
	private String groupId;
	
	@Value(value="${kafka.consumer.auto.commit}")
	private boolean consumerAutoCommit;
	
	@Value(value="${kafka.consumer.commit.interval}")
	private int commitInterval;
	
	@Value(value="${kafka.consumer.session.timeout}")
	private int sessionTimeout;
	
	@Value(value="${kafka.consumer.request.timeout}")
	private int requestTimeout;
	
	@Value(value="${kafka.Producer.retries}")
	private int retries;
	
	@Value(value="${kafka.Producer.batch.size}")
	private int batchSize;
	
	@Value(value="${kafka.Producer.linger}")
	private int linger;
	
	@Value(value="${kafka.Producer.buffer.memory}")
	private int bufferMemory;
	
	
	@Bean
	KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Object, Object>>
	    kafkaListenerContainerFactory() {
	SimpleKafkaListenerContainerFactory<Object, Object> factory =
	            new SimpleKafkaListenerContainerFactory<Object, Object>();
	factory.setConsumerFactory(consumerFactory());
	factory.setConcurrency(3);
	return factory;
	}

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
    	DefaultKafkaConsumerFactory<?, ?> consumerFactory = new DefaultKafkaConsumerFactory<Object, Object>(consumerConfigs());
        return (ConsumerFactory<Object, Object>) consumerFactory;
    }
    
    
    private Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, consumerAutoCommit);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, commitInterval);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);
        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeout);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "com.sohouer.core.kafka.serilizer.KafkaModelDeSerilizer");
        return props;
    }
    
    /**
     * ProducerFactory
     * @return
     */
    @Bean
    public ProducerFactory<Object, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<Object, Object>(senderProps());
    }

    /**
     * 
     * ProducerFactory 连接属性
     * @return
     */
    private Map<String, Object> senderProps() {
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 1000);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.sohouer.core.kafka.partitioner.SpringKafkaPartitioner");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.IntegerSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "com.sohouer.core.kafka.serilizer.KafkaModelSerilizer");
        return props;
    }
    
    /**
     * kafka message Template
     * @return
     */
    @Bean
    public KafkaTemplate<Object, Object> kafkaTemplate() {
        return new KafkaTemplate<Object, Object>(producerFactory());
    }
	
    
    
    
  

}
