package com.kvp.streams;

import com.kvp.domain.Introduce;
import com.kvp.streams.serdes.IntroduceSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;

//https://coding-start.tistory.com/138
public class StreamsApplication {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "kvp-streams");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, IntroduceSerde.class);

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, Introduce> firstStream = builder.stream("kvp-input", Consumed.with(Serdes.String(), new IntroduceSerde()));

        firstStream.foreach((key, value) -> System.out.println(String.format("value is %s", value.toString())));

        firstStream.mapValues(value -> {
            value.addAge();
            return value;
        });

        firstStream.to("kvp-output");

        Topology topology = builder.build();

        KafkaStreams kafkaStreams = new KafkaStreams(topology, properties);

        kafkaStreams.start();

        //TODO 이곳에 KafkaStreams 실습을 작성해 주시면 됩니다!
        /**
         * 1. kvp-input 토픽에서 Introduce 객체를 받아, kvp-output 토픽에 저장하는 기능을 구현하세요.
         * 2. kvp-input 토픽에서 받은 Introduce객체에 다음과 같은 작업을 진행합니다.
         *   - 나이를 10살 더해주세요.
         *   - 이름의 마스킹을 진행해 주세요.
         */
    }
}
