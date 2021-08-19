package com.kvp.kafka.step3;

import com.kvp.domain.AnonymousIntroduce;
import com.kvp.domain.Introduce;
import com.kvp.domain.step3.ReceiptWithGrade;
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
public class Step3ConsumerConfiguration {

    public static final String STEP3_LISTENER_NAME = "step3Listener";

    @Bean
    public ConsumerFactory<String, ReceiptWithGrade> receiptWithGradeConsumerConfigs() {
        Map<String, Object> configs = getDefaultConsumerConfigs();

        return new DefaultKafkaConsumerFactory<>(
                configs,
                new StringDeserializer(),
                new JsonDeserializer<>(ReceiptWithGrade.class));
    }

    @Bean(STEP3_LISTENER_NAME)
    public ConcurrentKafkaListenerContainerFactory<String, ReceiptWithGrade> introduceListener() {
        ConcurrentKafkaListenerContainerFactory<String, ReceiptWithGrade> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(receiptWithGradeConsumerConfigs());
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
