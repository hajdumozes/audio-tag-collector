package com.mozeshajdu.audiotagcollector.service;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("consumeAudioTags")
@Slf4j
public class KafkaConsumerImpl implements KafkaConsumer {

    @Override
    public void accept(AudioTag audioTag) {
        log.info("Received audioTag: {}", audioTag.toString());
    }
}
