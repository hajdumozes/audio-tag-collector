package com.mozeshajdu.audiotagcollector.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class KafkaProducer {
    Sinks.Many<String> audioTagSink;

    public void produce(String audioTag) {
        audioTagSink.tryEmitNext(audioTag);
    }
}
