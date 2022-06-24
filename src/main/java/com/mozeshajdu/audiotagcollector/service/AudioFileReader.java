package com.mozeshajdu.audiotagcollector.service;

import com.mozeshajdu.audiotagcollector.entity.AudioExtension;
import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.event.producer.AudioTagProducer;
import com.mozeshajdu.audiotagcollector.exception.AudioReadException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.util.Strings;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AudioFileReader {
    AudioTagExtractor audioTagExtractor;
    AudioTagProducer audioTagProducer;

    public void read(File file) {
        if (isAudioFile(file)) {
            AudioFile audioFile = readAudioFile(file);
            Tag tag = audioFile.getTag();
            AudioTag audioTag = audioTagExtractor.extract(tag);
            audioTagProducer.produce(audioTag);
        }
    }

    private List<String> getAllAudioExtensionPattern() {
        AudioExtension[] values = AudioExtension.values();
        return Arrays.stream(values).map(AudioExtension::getExtension).collect(Collectors.toList());
    }

    private AudioFile readAudioFile(File file) {
        try {
            return AudioFileIO.read(file);
        } catch (Exception e) {
            throw new AudioReadException(e.getMessage());
        }
    }

    private boolean isAudioFile(File file) {
        String fileExtension = getFileExtension(file.getName()).orElse(Strings.EMPTY);
        return getAllAudioExtensionPattern().contains(fileExtension);
    }

    private Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(name -> name.contains("."))
                .map(name -> name.substring(filename.lastIndexOf(".")));
    }
}
