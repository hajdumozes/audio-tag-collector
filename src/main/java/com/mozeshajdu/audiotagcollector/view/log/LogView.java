package com.mozeshajdu.audiotagcollector.view.log;

import com.mozeshajdu.audiotagcollector.entity.ProcessingStatus;
import com.mozeshajdu.audiotagcollector.entity.TagTableEntry;
import de.saxsys.mvvmfx.FxmlView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LogView implements FxmlView<LogViewModel> {
    @FXML
    TableView<TagTableEntry> tableView;

    @FXML
    TableColumn<TagTableEntry, String> title;

    @FXML
    TableColumn<TagTableEntry, String> album;

    @FXML
    TableColumn<TagTableEntry, ProcessingStatus> status;

    public void addToTable(TagTableEntry entry) {
        if (!tableView.getItems().contains(entry)) {
            tableView.getItems().add(entry);
        }
    }

    public Optional<Integer> findIndex(TagTableEntry entry) {
        ObservableList<TagTableEntry> items = tableView.getItems();
        if (items.contains(entry)) {
            return Optional.of(items.indexOf(entry));
        }
        return Optional.empty();
    }

    public void setEntry(int index, TagTableEntry entry) {
        tableView.getItems().set(index, entry);
    }
}
