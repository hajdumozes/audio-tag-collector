package com.mozeshajdu.audiotagcollector.view;

import com.mozeshajdu.audiotagcollector.AudioTagCollectorApplication;
import com.mozeshajdu.audiotagcollector.view.audiocollector.AudioCollectorView;
import com.mozeshajdu.audiotagcollector.view.log.LogView;
import de.saxsys.mvvmfx.FluentViewLoader;
import de.saxsys.mvvmfx.FxmlView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainView implements FxmlView<MainViewModel> {
    public static final String DRAG_AND_DROP_STAGE_TITLE = "Local collector";
    public static final String LOG_STAGE_TITLE = "Logs";

    @FXML
    Button dragAndDropButton;

    @FXML
    Button logButton;

    public void dragAndDropButtonClicked() {
        Stage stage = createDragAndDropWindow();
        stage.setOnCloseRequest(e -> dragAndDropButton.setDisable(false));
        dragAndDropButton.setDisable(true);
        stage.show();
    }

    public void logButtonClicked() {
        Stage stage = createLogWindow();
        stage.setOnCloseRequest(e -> logButton.setDisable(false));
        logButton.setDisable(true);
        stage.show();
    }

    private Stage createDragAndDropWindow() {
        Parent root;
        root = FluentViewLoader.fxmlView(AudioCollectorView.class).load().getView();
        Stage stage = new Stage();
        stage.getIcons().add(AudioTagCollectorApplication.APPLICATION_ICON);
        stage.setTitle(DRAG_AND_DROP_STAGE_TITLE);
        stage.setScene(new Scene(root));
        return stage;
    }

    private Stage createLogWindow() {
        Parent root;
        root = FluentViewLoader.fxmlView(LogView.class).load().getView();
        Stage stage = new Stage();
        stage.getIcons().add(AudioTagCollectorApplication.APPLICATION_ICON);
        stage.setTitle(LOG_STAGE_TITLE);
        stage.setScene(new Scene(root));
        return stage;
    }
}
