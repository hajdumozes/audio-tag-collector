package com.mozeshajdu.audiotagcollector.event.consumer;

import com.mozeshajdu.audiotagcollector.entity.ProcessingStatus;
import com.mozeshajdu.audiotagcollector.entity.TagTableEntry;
import com.mozeshajdu.audiotagcollector.event.entity.AudioTagAddedMessage;
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
@Qualifier("consumeAudioTagAdded")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class AudioTagAddedConsumer implements Consumer<AudioTagAddedMessage> {
    AudioTagMapper audioTagMapper;
    AudioCollectorViewModel audioCollectorViewModel;
    LogViewModel logView;

    @Override
    public void accept(AudioTagAddedMessage audioTagAddedMessage) {
        log.info("Received audioTagAddedMessage: {}", audioTagAddedMessage.toString());
        audioCollectorViewModel.increaseAdded();
        audioCollectorViewModel.moveProgressBar();
        TagTableEntry entry = audioTagMapper.ofAddedMessage(audioTagAddedMessage);
        Optional<Integer> entryIndex = logView.findIndex(entry);
        entryIndex.ifPresent(index -> logView.setEntryStatus(index, ProcessingStatus.ADDED));
    }
}
