package com.mozeshajdu.audiotagcollector.view.audiocollector;

import com.mozeshajdu.audiotagcollector.AudioTagCollectorApplication;
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

    @FXML
    Button dragAndDropButton;

    public void buttonClicked() {
        Stage stage = createDragAndDropWindow();
        stage.setOnCloseRequest(e -> dragAndDropButton.setDisable(false));
        dragAndDropButton.setDisable(true);
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
}
