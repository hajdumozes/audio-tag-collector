package com.mozeshajdu.audiotagcollector.config;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class EventProducerConfig {

    @Bean
    public Sinks.Many<AudioTag> audioTagSink() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    Supplier<Flux<AudioTag>> produceAudioTags(Sinks.Many<AudioTag> audioTagSink) {
        return audioTagSink::asFlux;
    }
}
