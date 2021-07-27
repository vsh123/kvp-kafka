package com.kvp.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KvpTestConsumer {

    @KafkaListener(topics = "kvp-test", groupId = "kvp")
    public void consume(String message) {
        log.info("consume message : {}", message);
    }
}
