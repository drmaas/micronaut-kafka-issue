package org.example;

import io.micronaut.configuration.kafka.streams.ConfiguredStreamBuilder;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Value;
import java.util.Properties;
import javax.inject.Named;
import javax.inject.Singleton;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

@Factory
public class StreamFactory {

    @Singleton
    @Named("stream-1")
    KStream<String, String> stream1(@Value("${topic1}") String topic, @Named("stream-1") ConfiguredStreamBuilder builder) {

        Properties props = builder.getConfiguration();
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        KStream<String, String> source = builder.stream(topic);
        source.foreach((k,v) -> System.out.println("Received from stream1 " + v));
        return source;
    }

    @Singleton
    @Named("stream-2")
    KStream<String, String> stream2(@Value("${topic2}") String topic, @Named("stream-2") ConfiguredStreamBuilder builder) {

        Properties props = builder.getConfiguration();
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        KStream<String, String> source = builder.stream(topic);
        source.foreach((k,v) -> System.out.println("Received from stream 2" + v));
        return source;
    }

}
