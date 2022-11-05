package com.mozeshajdu.audiotagcollector.view.audiocollector;

import de.saxsys.mvvmfx.ViewModel;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AudioCollectorViewModel implements ViewModel {
    int processedCounter;
    int addedCounter;
    @Setter
    double progressBarUnit;
    @Setter
    int goal;
    final ReadOnlyStringWrapper processedLabelMessage = new ReadOnlyStringWrapper();
    final ReadOnlyStringWrapper addedLabelMessage = new ReadOnlyStringWrapper();
    final ReadOnlyDoubleWrapper progressBar = new ReadOnlyDoubleWrapper();

    public void resetProcessed() {
        processedCounter = 0;
        Platform.runLater(() -> progressBar.set(0));
    }

    public void increaseProcessed() {
        processedCounter++;
        Platform.runLater(() -> processedLabelMessage.set(String.format("Processed tags: %d/%d", processedCounter, goal)));
    }

    public void increaseAdded() {
        addedCounter++;
        Platform.runLater(() -> addedLabelMessage.set(String.format("New tags: %d", addedCounter)));
    }

    public void moveProgressBar() {
        Platform.runLater(() -> progressBar.set(progressBar.get() + progressBarUnit));
    }

    public ReadOnlyStringProperty getProcessedLabelMessage() {
        return processedLabelMessage.getReadOnlyProperty();
    }

    public ReadOnlyStringProperty getAddedLabelMessage() {
        return addedLabelMessage.getReadOnlyProperty();
    }

    public ReadOnlyDoubleProperty getProgress() {
        return progressBar.getReadOnlyProperty();
    }
}
