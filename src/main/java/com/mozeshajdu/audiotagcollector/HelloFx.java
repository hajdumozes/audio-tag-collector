package com.mozeshajdu.audiotagcollector;

import com.mozeshajdu.audiotagcollector.entity.AudioExtension;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HelloFx extends Application {

    @Override
    @SneakyThrows
    public void start(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(stage);
        AudioFile read = AudioFileIO.read(file);
        Tag tag = read.getTag();
    }

    public static void main(String[] args) {
        launch();
    }

    private void configureFileChooser(FileChooser fileChooser) {
        fileChooser.setTitle("Choose audio files");
        FileChooser.ExtensionFilter audioFiles = new FileChooser.ExtensionFilter("Audio files",
                getAllAudioExtensionPattern());
        fileChooser.getExtensionFilters().add(audioFiles);
    }

    private List<String> getAllAudioExtensionPattern() {
        AudioExtension[] values = AudioExtension.values();
        return Arrays.stream(values).map(AudioExtension::getFilePattern).collect(Collectors.toList());
    }
}
