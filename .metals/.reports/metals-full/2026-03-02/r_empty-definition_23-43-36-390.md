error id: file:///D:/CODING/JAVA/consumer-service/src/main/java/com/example/consumer_service/config/KafkaConsumerConfig.java:_empty_/KafkaConsumerProperties#
file:///D:/CODING/JAVA/consumer-service/src/main/java/com/example/consumer_service/config/KafkaConsumerConfig.java
empty definition using pc, found symbol in pc: _empty_/KafkaConsumerProperties#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 778
uri: file:///D:/CODING/JAVA/consumer-service/src/main/java/com/example/consumer_service/config/KafkaConsumerConfig.java
text:
```scala
package com.example.consumer_service.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private KafkaConsumerPr@@operties kafkaConsumerProperties;

    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConsumerProperties.getBootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerProperties.getGroupId());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, kafkaConsumerProperties.getEnableAutoCommit());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConsumerProperties.getAutoOffsetReset());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaConsumerProperties.getMaxPoolRecords());
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, kafkaConsumerProperties.getFetchMinBytes());
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, kafkaConsumerProperties.getFetchMaxWaitMs());

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        factory.setBatchListener(true); // aktifkan batch
        factory.setConcurrency(3); // thread paralel
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        return factory;
    }

}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/KafkaConsumerProperties#