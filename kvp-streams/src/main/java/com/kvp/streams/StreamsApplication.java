package com.kvp.streams;

import com.kvp.domain.step3.Receipt;
import com.kvp.domain.step3.ReceiptWithGrade;
import com.kvp.streams.step3.ReceiptSerde;
import com.kvp.streams.step3.ReceiptWithGradeSerde;
import com.kvp.streams.step3.ReceiptWithGradeTransformer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;

import java.util.Properties;

//https://coding-start.tistory.com/138
public class StreamsApplication {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "kvp-streams");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, Receipt> receiptStream = builder.stream("step3-input", Consumed.with(Serdes.String(), new ReceiptSerde()));
        receiptStream.print(Printed.<String, Receipt>toSysOut().withLabel("receipt input"));

        String totalPurchaseStoreName = "totalPurchaseStore";
        KeyValueBytesStoreSupplier supplier = Stores.inMemoryKeyValueStore(totalPurchaseStoreName);
        StoreBuilder<KeyValueStore<String, Long>> storeBuilder = Stores.keyValueStoreBuilder(supplier, Serdes.String(), Serdes.Long())
                .withLoggingDisabled();

        builder.addStateStore(storeBuilder);

        receiptStream
                .transformValues(() -> new ReceiptWithGradeTransformer(totalPurchaseStoreName), totalPurchaseStoreName)
                .to("step3-output", Produced.with(Serdes.String(), new ReceiptWithGradeSerde()));

        Topology topology = builder.build();

        KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);

        kafkaStreams.start();
    }
}
