package com.mozeshajdu.audiotagcollector.event;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AudioTagProducer {
    Sinks.Many<AudioTag> audioTagSink;

    public void produce(AudioTag audioTag) {
        audioTagSink.tryEmitNext(audioTag);
    }
}
