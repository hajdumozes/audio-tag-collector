package com.mozeshajdu.audiotagcollector.ui;

import com.mozeshajdu.audiotagcollector.entity.AudioExtension;
import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.event.AudioTagProducer;
import com.mozeshajdu.audiotagcollector.exception.AudioReadException;
import com.mozeshajdu.audiotagcollector.service.AudioTagExtractor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
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
    AudioTagProducer audioTagProducer;
    GridPane gridPane;
    Pane pane;

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        Button selectDirectoryButton = setupSelectDirectoryButton(stage);
        Button selectAudioFilesButton = setupSelectAudioFilesButton(stage);
        configurePane(stage, selectAudioFilesButton, selectDirectoryButton);
        stage.show();
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

    private Button setupSelectAudioFilesButton(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);
        Button selectAudioFilesButton = new Button("Select audio files...");
        selectAudioFilesButton.setOnAction(event -> consumeAudioFiles(stage, fileChooser));
        return selectAudioFilesButton;
    }

    private void consumeAudioFiles(Stage stage, FileChooser fileChooser) {
        List<File> files = fileChooser.showOpenMultipleDialog(stage);
        List<AudioTag> audioTags = files.stream().map(this::toAudioTag).collect(Collectors.toList());
        audioTags.forEach(audioTagProducer::produce);
    }

    private Button setupSelectDirectoryButton(Stage stage) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Button selectAudioFilesButton = new Button("Select directory...");
        selectAudioFilesButton.setOnAction(event -> System.out.println("not yet implemented"));
        return selectAudioFilesButton;
    }

    private void configurePane(Stage stage, Button selectAudioFilesButton, Button selectDirectoryButton) {
        GridPane.setConstraints(selectAudioFilesButton, 0, 0);
        GridPane.setConstraints(selectDirectoryButton, 1, 0);
        gridPane.getChildren().addAll(selectAudioFilesButton, selectDirectoryButton);
        pane.getChildren().addAll(gridPane);
        stage.setScene(new Scene(pane));
    }
}
