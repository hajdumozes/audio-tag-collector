package com.mozeshajdu.audiotagcollector.ui;

import com.mozeshajdu.audiotagcollector.entity.AudioExtension;
import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.exception.AudioReadException;
import com.mozeshajdu.audiotagcollector.service.AudioTagExtractor;
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


    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(stage);
        AudioFile audioFile = readAudioFile(file);
        Tag tag = audioFile.getTag();
        AudioTag audioTag = audioTagExtractor.extract(tag);
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
}
