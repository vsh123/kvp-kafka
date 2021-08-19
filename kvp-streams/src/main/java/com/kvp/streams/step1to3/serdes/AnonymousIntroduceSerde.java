package com.kvp.streams.step1to3.serdes;

import com.kvp.domain.AnonymousIntroduce;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class AnonymousIntroduceSerde extends Serdes.WrapperSerde<AnonymousIntroduce> {
    public AnonymousIntroduceSerde() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(AnonymousIntroduce.class));
    }
}
