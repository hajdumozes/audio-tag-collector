package com.mozeshajdu.audiotagcollector.view.log;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.entity.ProcessingStatus;
import de.saxsys.mvvmfx.FxmlView;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogView implements FxmlView<LogViewModel> {
    @FXML
    TableView<AudioTag> tableView;

    @FXML
    TableColumn<AudioTag, String> title;

    @FXML
    TableColumn<AudioTag, String> album;

    @FXML
    TableColumn<AudioTag, ProcessingStatus> status;

    public void addToTable(AudioTag audioTag) {
        tableView.getItems().add(audioTag);
    }
}
