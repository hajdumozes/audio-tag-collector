package com.mozeshajdu.audiotagcollector.service;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.event.producer.AudioTagProducer;
import com.mozeshajdu.audiotagcollector.exception.AudioReadException;
import com.mozeshajdu.audiotagcollector.view.log.LogView;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AudioFileReader {
    AudioTagExtractor audioTagExtractor;
    AudioTagProducer audioTagProducer;
    LogView logView;

    public void read(File file) {
        AudioFile audioFile = readAudioFile(file);
        Tag tag = audioFile.getTag();
        AudioTag audioTag = audioTagExtractor.extract(tag);
        audioTagProducer.produce(audioTag);
        logView.addToTable(audioTag);
    }


    private AudioFile readAudioFile(File file) {
        try {
            return AudioFileIO.read(file);
        } catch (Exception e) {
            throw new AudioReadException(e.getMessage());
        }
    }
}
