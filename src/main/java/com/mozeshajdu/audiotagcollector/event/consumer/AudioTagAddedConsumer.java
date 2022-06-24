package com.mozeshajdu.audiotagcollector.event.consumer;

import com.mozeshajdu.audiotagcollector.event.entity.AudioTagAddedMessage;
import com.mozeshajdu.audiotagcollector.view.audiocollector.AudioCollectorViewModel;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Qualifier("consumeAudioTagAdded")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class AudioTagAddedConsumer implements Consumer<AudioTagAddedMessage> {

    AudioCollectorViewModel audioCollectorViewModel;

    @Override
    public void accept(AudioTagAddedMessage audioTagAddedMessage) {
        log.info("Received audioTagAddedMessage: {}", audioTagAddedMessage.toString());
        audioCollectorViewModel.increaseAdded();
    }
}
