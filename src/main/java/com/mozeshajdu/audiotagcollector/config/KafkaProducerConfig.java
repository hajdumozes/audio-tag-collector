package com.mozeshajdu.audiotagcollector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public Sinks.Many<String> audioTagSink() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    Supplier<Flux<String>> produceAudioTags(Sinks.Many<String> audioTagSink) {
        return audioTagSink::asFlux;
    }
}
