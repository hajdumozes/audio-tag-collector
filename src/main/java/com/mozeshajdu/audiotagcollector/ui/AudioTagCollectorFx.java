package com.mozeshajdu.audiotagcollector.ui;

import com.mozeshajdu.audiotagcollector.AudioTagCollectorApplication;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class AudioTagCollectorFx extends Application {

    ConfigurableApplicationContext applicationContext;

    @Override
    public void start(Stage stage) {
        applicationContext.publishEvent(new StageReadyEvent(stage));

    }

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(AudioTagCollectorApplication.class).run();
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }
}
