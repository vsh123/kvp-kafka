package com.kvp.streams.step3;

import com.kvp.domain.step3.Receipt;
import com.kvp.domain.step3.ReceiptWithGrade;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

public class ReceiptWithGradeSerde extends Serdes.WrapperSerde<ReceiptWithGrade> {

    public ReceiptWithGradeSerde() {
        super(new JsonSerializer<>(), new JsonDeserializer<>(ReceiptWithGrade.class));
    }
}
