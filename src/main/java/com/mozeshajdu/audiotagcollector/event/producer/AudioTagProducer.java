package com.mozeshajdu.audiotagcollector.event.producer;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.event.entity.AudioTagCreatedMessage;
import com.mozeshajdu.audiotagcollector.mapper.AudioTagMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Sinks;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AudioTagProducer {
    Sinks.Many<AudioTagCreatedMessage> audioTagCreatedMessageMany;
    AudioTagMapper audioTagMapper;

    public void produce(AudioTag audioTag) {
        audioTagCreatedMessageMany.tryEmitNext(audioTagMapper.of(audioTag));
    }
}
