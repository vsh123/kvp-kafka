package com.kvp.kafka.step3;

import com.kvp.domain.AnonymousIntroduce;
import com.kvp.domain.Introduce;
import com.kvp.domain.step3.ReceiptWithGrade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.kvp.kafka.consumer.ConsumerConfiguration.ANONYMOUS_INTRODUCE_LISTENER_NAME;
import static com.kvp.kafka.consumer.ConsumerConfiguration.INTRODUCE_LISTENER_NAME;
import static com.kvp.kafka.step3.Step3ConsumerConfiguration.STEP3_LISTENER_NAME;

@Slf4j
@Component
public class Step3Consumer {

    @KafkaListener(topics = "step3-output", groupId = "kvp", containerFactory = STEP3_LISTENER_NAME)
    public void step3Consume(ReceiptWithGrade message) {
        log.info("등급과 함께 입장하십니다 : {}", message);
    }
}
