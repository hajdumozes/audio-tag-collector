package com.mozeshajdu.audiotagcollector;

import com.mozeshajdu.audiotagcollector.view.MainView;
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
    public static final Image APPLICATION_ICON = new Image("spotify_icon.png");
    private static final String APP_TITLE = "Audio collector";

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void startMvvmfx(Stage stage) {
        Parent root = FluentViewLoader.fxmlView(MainView.class).load().getView();
        stage.getIcons().add(APPLICATION_ICON);
        stage.setTitle(APP_TITLE);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
