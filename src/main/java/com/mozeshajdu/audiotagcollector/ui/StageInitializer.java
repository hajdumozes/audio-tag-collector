package com.mozeshajdu.audiotagcollector.ui;

import com.mozeshajdu.audiotagcollector.entity.AudioExtension;
import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.exception.AudioReadException;
import com.mozeshajdu.audiotagcollector.service.AudioTagExtractor;
import com.mozeshajdu.audiotagcollector.service.KafkaProducer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    public static final String FILE_CHOOSER_TITLE = "Choose audio files";
    public static final String EXTENSION_FILTER_NAME = "Audio files";

    AudioTagExtractor audioTagExtractor;
    KafkaProducer kafkaProducer;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        List<File> files = fileChooser.showOpenMultipleDialog(stage);
        List<AudioTag> audioTags = files.stream().map(this::toAudioTag).collect(Collectors.toList());
        audioTags.forEach(audioTag -> kafkaProducer.produce(audioTag.getTitle()));
    }

    private void configureFileChooser(FileChooser fileChooser) {
        fileChooser.setTitle(FILE_CHOOSER_TITLE);
        FileChooser.ExtensionFilter audioFiles = new FileChooser.ExtensionFilter(EXTENSION_FILTER_NAME,
                getAllAudioExtensionPattern());
        fileChooser.getExtensionFilters().add(audioFiles);
    }

    private List<String> getAllAudioExtensionPattern() {
        AudioExtension[] values = AudioExtension.values();
        return Arrays.stream(values).map(AudioExtension::getFilePattern).collect(Collectors.toList());
    }

    private AudioFile readAudioFile(File file) {
        try {
            return AudioFileIO.read(file);
        } catch (Exception e) {
            throw new AudioReadException(e.getMessage());
        }
    }

    private AudioTag toAudioTag(File file) {
        AudioFile audioFile = readAudioFile(file);
        Tag tag = audioFile.getTag();
        return audioTagExtractor.extract(tag);
    }
}
