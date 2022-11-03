package com.mozeshajdu.audiotagcollector;

import com.mozeshajdu.audiotagcollector.view.audiocollector.AudioCollectorView;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.spring.MvvmfxSpringApplication;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AudioTagCollectorApplication extends MvvmfxSpringApplication {
    private static final String APP_TITLE = "Audio collector";
    private static final String ICON_NAME = "spotify_icon.png";
    private static final int SCENE_WIDTH = 500;
    private static final int SCENE_HEIGHT = 300;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void startMvvmfx(Stage stage) {
        Parent root = FluentViewLoader.fxmlView(AudioCollectorView.class).load().getView();

        stage.getIcons().add(new Image(ICON_NAME));
        stage.setTitle(APP_TITLE);
        stage.show();
        stage.setScene(new Scene(root, SCENE_WIDTH, SCENE_HEIGHT));
        stage.show();
    }
}
