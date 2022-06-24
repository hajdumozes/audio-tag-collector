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


    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void startMvvmfx(Stage stage) {
        Parent root = FluentViewLoader.fxmlView(AudioCollectorView.class).load().getView();

        stage.getIcons().add(new Image("spotify_icon.png"));
        stage.setTitle("Audio collector");
        stage.show();
        stage.setScene(new Scene(root, 500, 300));
        stage.show();
    }
}
