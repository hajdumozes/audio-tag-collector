package com.mozeshajdu.audiotagcollector.event.consumer;

import com.mozeshajdu.audiotagcollector.entity.ProcessingStatus;
import com.mozeshajdu.audiotagcollector.entity.TagTableEntry;
import com.mozeshajdu.audiotagcollector.event.entity.AudioTagProcessedMessage;
import com.mozeshajdu.audiotagcollector.mapper.AudioTagMapper;
import com.mozeshajdu.audiotagcollector.view.audiocollector.AudioCollectorViewModel;
import com.mozeshajdu.audiotagcollector.view.log.LogViewModel;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;

@Component
@Qualifier("consumeAudioTagProcessed")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class AudioTagProcessedConsumer implements Consumer<AudioTagProcessedMessage> {
    AudioTagMapper audioTagMapper;
    AudioCollectorViewModel audioCollectorViewModel;
    LogViewModel logViewModel;

    @Override
    public void accept(AudioTagProcessedMessage audioTagProcessedMessage) {
        log.info("Received audioTagProcessedMessage: {}", audioTagProcessedMessage.toString());
        audioCollectorViewModel.increaseProcessed();
        TagTableEntry entry = audioTagMapper.ofProcessedMessage(audioTagProcessedMessage);
        Optional<Integer> entryIndex = logViewModel.findIndex(entry);
        entryIndex.ifPresent(index -> logViewModel.setEntryStatus(index, ProcessingStatus.PROCESSED));
    }
}
