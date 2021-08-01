package com.kvp.streams;


import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.kvp.domain.Introduce;
import com.kvp.streams.serdes.IntroduceSerde;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;

//https://coding-start.tistory.com/138
public class StreamsApplication {
    public static void main(String[] args) {
        /*
         * 카프카 스트림 파이프 프로세스에 필요한 설정값
         * StreamsConfig의 자세한 설정값은
         * https://kafka.apache.org/10/documentation/#streamsconfigs 참고
         */
        Properties props = new Properties();
        //카프카 스트림즈 애플리케이션을 유일할게 구분할 아이디
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kvp-streams");
        //스트림즈 애플리케이션이 접근할 카프카 브로커정보
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        //데이터를 어떠한 형식으로 Read/Write할지를 설정(키/값의 데이터 타입을 지정) - 문자열
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, IntroduceSerde.class);

        Serde<String> serdeString = Serdes.String();
        Serde<Introduce> serdeIntroduce = new IntroduceSerde();

        //데이터의 흐름으로 구성된 토폴로지를 정의할 빌더
        final StreamsBuilder builder = new StreamsBuilder();

        KStream<String, Introduce> firstSerde = builder.stream("kvp-input");

        firstSerde.foreach((k, v) -> {
            System.out.println(String.format("value is : %s", v.toString()));
        });

        firstSerde.mapValues(value -> {
           value.addAge();
           return value;
        });

        firstSerde.to("kvp-output");

        //최종적인 토폴로지 생성
        final Topology topology = builder.build();

        final KafkaStreams streams = new KafkaStreams(topology, props);

        streams.start();
    }
}
