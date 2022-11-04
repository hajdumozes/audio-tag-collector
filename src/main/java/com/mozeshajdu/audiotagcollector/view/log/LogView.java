package com.mozeshajdu.audiotagcollector.view.log;

import com.mozeshajdu.audiotagcollector.entity.TagTableEntry;
import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
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
    TableView<TagTableEntry> tableView;

    @InjectViewModel
    private LogViewModel viewModel;


    public void initialize() {
        tableView.setItems(viewModel.getEntries());
    }
}
