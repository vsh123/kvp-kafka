package com.kvp.kafka.consumer;

import com.kvp.domain.AnonymousIntroduce;
import com.kvp.domain.Introduce;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfiguration {

    public static final String INTRODUCE_LISTENER_NAME = "introduceListener";
    public static final String ANONYMOUS_INTRODUCE_LISTENER_NAME = "anonymousIntroduceListener";

    @Bean
    public ConsumerFactory<String, Introduce> introduceConsumerConfigs() {
        Map<String, Object> configs = getDefaultConsumerConfigs();

        return new DefaultKafkaConsumerFactory<>(
                configs,
                new StringDeserializer(),
                new JsonDeserializer<>(Introduce.class));
    }

    @Bean(INTRODUCE_LISTENER_NAME)
    public ConcurrentKafkaListenerContainerFactory<String, Introduce> introduceListener() {
        ConcurrentKafkaListenerContainerFactory<String, Introduce> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(introduceConsumerConfigs());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, AnonymousIntroduce> anonymousIntroduceConsumerConfigs() {

        Map<String, Object> configs = getDefaultConsumerConfigs();

        return new DefaultKafkaConsumerFactory<>(
                configs,
                new StringDeserializer(),
                new JsonDeserializer<>(AnonymousIntroduce.class));
    }

    @Bean(ANONYMOUS_INTRODUCE_LISTENER_NAME)
    public ConcurrentKafkaListenerContainerFactory<String, AnonymousIntroduce> anonymousIntroduceListener() {
        ConcurrentKafkaListenerContainerFactory<String, AnonymousIntroduce> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(anonymousIntroduceConsumerConfigs());
        return factory;
    }

    private Map<String, Object> getDefaultConsumerConfigs() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "kvp");
        configs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return configs;
    }
}
