package com.kvp.streams.step3;

import com.kvp.domain.step3.Receipt;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class ReceiptSerde extends Serdes.WrapperSerde<Receipt> {

    public ReceiptSerde() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(Receipt.class));
    }
}
