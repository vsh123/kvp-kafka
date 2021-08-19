package com.kvp.kafka.step3;

import com.kvp.domain.Introduce;
import com.kvp.domain.step3.Receipt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Step3Producer {
    private static final String TOPIC = "step3-input";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Step3Producer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Receipt receipt) {
        log.info("produce message : {}", receipt);
        kafkaTemplate.send(TOPIC, receipt);
    }
}
