package com.mozeshajdu.audiotagcollector.event;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
@Qualifier("consumeAudioTags")
@Slf4j
public class AudioTagConsumer implements Consumer<AudioTag> {

    @Override
    public void accept(AudioTag audioTag) {
        log.info("Received audioTag: {}", audioTag.toString());
    }
}
