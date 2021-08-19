package com.kvp.streams.step1to3;

import com.kvp.domain.AnonymousIntroduce;
import com.kvp.domain.Introduce;
import com.kvp.streams.step1to3.serdes.AnonymousIntroduceSerde;
import com.kvp.streams.step1to3.serdes.IntroduceSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;

public class Step1To3Builder {
    public static Topology getTopology() {
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, Introduce> firstStream = builder.stream("kvp-input", Consumed.with(Serdes.String(), new IntroduceSerde()));
        firstStream.print(Printed.<String,Introduce>toSysOut().withLabel("firstStream"));

        Predicate<String, Introduce> isJunior = (key, introduce) -> introduce.isJunior();
        Predicate<String, Introduce> isSenior = (key, introduce) -> introduce.isSenior();
        int junior = 0;
        int senior = 1;
        KStream<String, Introduce>[] engineerStreams =
                firstStream.branch(isJunior, isSenior);

        KStream<String, Introduce> juniorEngineerStream = engineerStreams[junior];
        juniorEngineerStream.print(Printed.<String, Introduce>toSysOut().withLabel("juniorEngineer"));
        juniorEngineerStream.to("junior-engineer", Produced.with(Serdes.String(), new IntroduceSerde()));

        KStream<String, Introduce> seniorEngineerStream = engineerStreams[senior];
        seniorEngineerStream.print(Printed.<String, Introduce>toSysOut().withLabel("seniorEngineer"));
        seniorEngineerStream.to("senior-engineer", Produced.with(Serdes.String(), new IntroduceSerde()));

        KStream<String, Introduce> seniorJavaEngineer = seniorEngineerStream.filter(((key, value) -> value.isJavaEngineer()));
        seniorJavaEngineer.print(Printed.<String, Introduce>toSysOut().withLabel("seniorJavaEngineer"));
        KStream<String, AnonymousIntroduce> seniorJavaStream = seniorJavaEngineer
                //SelectKey를 하고 mapValues를 하게되면 자동으로 리파티셔닝을 유도할 수 있음
                .selectKey((k,v) -> v.getName())
                .mapValues(introduce -> new AnonymousIntroduce(introduce.getAge(), introduce.getAnnual()));
        seniorJavaStream.print(Printed.<String, AnonymousIntroduce>toSysOut().withLabel("seniorJavaStream"));

        seniorJavaStream.to("senior-java-engineer", Produced.with(Serdes.String(), new AnonymousIntroduceSerde()));

        return builder.build();
    }
}
