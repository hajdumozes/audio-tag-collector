package com.mozeshajdu.audiotagcollector;

import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.Tag;

import java.io.File;

public class HelloFx extends Application {

    @Override
    @SneakyThrows
    public void start(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);
        AudioFile read = AudioFileIO.read(file);
        Tag tag = read.getTag();
    }

    public static void main(String[] args) {
        launch();
    }
}
