package com.kvp.kafka.consumer;

import com.kvp.domain.AnonymousIntroduce;
import com.kvp.domain.Introduce;
import static com.kvp.kafka.consumer.ConsumerConfiguration.ANONYMOUS_INTRODUCE_LISTENER_NAME;
import static com.kvp.kafka.consumer.ConsumerConfiguration.INTRODUCE_LISTENER_NAME;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EngineerConsumer {

    @KafkaListener(topics = "junior-engineer", groupId = "kvp", containerFactory = INTRODUCE_LISTENER_NAME)
    public void juniorEngineerConsume(Introduce message) {
        log.info("주니어 개발자 입장하십니다 : {}", message);
    }

    @KafkaListener(topics = "senior-engineer", groupId = "kvp", containerFactory = INTRODUCE_LISTENER_NAME)
    public void seniorEngineerConsume(Introduce message) {
        log.info("시니어 개발자 입장하십니다 : {}", message);
    }

    @KafkaListener(topics = "senior-java-engineer", groupId = "kvp", containerFactory = ANONYMOUS_INTRODUCE_LISTENER_NAME)
    public void seniorJavaEngineerConsume(AnonymousIntroduce message) {
        log.info("시니어 자바 개발자 입장하십니다 : {}", message);
    }
}
