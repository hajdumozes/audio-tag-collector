package com.mozeshajdu.audiotagcollector.view.log;

import com.mozeshajdu.audiotagcollector.entity.ProcessingStatus;
import com.mozeshajdu.audiotagcollector.entity.TagTableEntry;
import de.saxsys.mvvmfx.ViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LogViewModel implements ViewModel {
    ObservableList<TagTableEntry> entries = FXCollections.observableArrayList();

    public ObservableList<TagTableEntry> getEntries() {
        return entries;
    }

    public void addToTable(TagTableEntry entry) {
        if (!entries.contains(entry)) {
            entries.add(entry);
        }
    }

    public Optional<Integer> findIndex(TagTableEntry entry) {
        ObservableList<TagTableEntry> items = entries;
        if (items.contains(entry)) {
            return Optional.of(items.indexOf(entry));
        }
        return Optional.empty();
    }

    public void setEntryStatus(int index, ProcessingStatus status) {
        TagTableEntry tagTableEntry = entries.get(index);
        tagTableEntry.setProcessingStatus(status);
    }
}
