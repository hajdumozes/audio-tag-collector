package com.mozeshajdu.audiotagcollector.event.producer;

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
    Sinks.Many<AudioTag> audioTagMany;

    public void produce(AudioTag audioTag) {
        audioTagMany.tryEmitNext(audioTag);
    }
}
