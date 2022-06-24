package com.mozeshajdu.audiotagcollector.event.consumer;

import com.mozeshajdu.audiotagcollector.event.entity.AudioTagProcessedMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Qualifier("consumeAudioTagProcessed")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class AudioTagProcessedConsumer implements Consumer<AudioTagProcessedMessage> {

    @Override
    public void accept(AudioTagProcessedMessage audioTagProcessedMessage) {
        log.info("Received audioTagProcessedMessage: {}", audioTagProcessedMessage.toString());
    }
}
