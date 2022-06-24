package com.mozeshajdu.audiotagcollector.config;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.event.entity.AudioTagCreatedMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.function.Supplier;

@Configuration
public class EventProducerConfig {

    @Bean
    public Sinks.Many<AudioTagCreatedMessage> audioTagCreatedMessageMany() {
        return Sinks.many().unicast().onBackpressureBuffer();
    }

    @Bean
    Supplier<Flux<AudioTagCreatedMessage>> produceAudioTagCreated(Sinks.Many<AudioTagCreatedMessage> audioTagCreatedMessageMany) {
        return audioTagCreatedMessageMany::asFlux;
    }
}
