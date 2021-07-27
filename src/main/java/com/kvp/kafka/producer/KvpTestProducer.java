package com.kvp.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KvpTestProducer {
    private static final String TOPIC = "kvp-test";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KvpTestProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String message) {
        log.info("produce message : {}", message);
        kafkaTemplate.send(TOPIC, message);
    }
}
