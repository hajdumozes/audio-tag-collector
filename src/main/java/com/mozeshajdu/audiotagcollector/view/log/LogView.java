package com.mozeshajdu.audiotagcollector.view.log;

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
    TableView<String> tableView;

    @FXML
    TableColumn<String, String> fileName;
}
