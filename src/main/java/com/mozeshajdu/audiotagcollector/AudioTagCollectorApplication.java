package com.mozeshajdu.audiotagcollector;

import com.mozeshajdu.audiotagcollector.ui.AudioTagCollectorFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AudioTagCollectorApplication {

    public static void main(String[] args) {
        Application.launch(AudioTagCollectorFx.class, args);
    }

}
