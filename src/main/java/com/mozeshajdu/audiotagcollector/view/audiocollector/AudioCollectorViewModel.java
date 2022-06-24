package com.mozeshajdu.audiotagcollector.view.audiocollector;

import de.saxsys.mvvmfx.ViewModel;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AudioCollectorViewModel implements ViewModel {
    int counter;
    @Setter
    int goal;
    final ReadOnlyStringWrapper processedLabelMessage = new ReadOnlyStringWrapper();

    public void resetProcessed() {
        counter = 0;
    }

    public void increaseProcessed() {
        counter++;
        Platform.runLater(() -> processedLabelMessage.set(String.format("Processed tags: %d/%d", counter, goal)));
    }

    public ReadOnlyStringProperty getProcessedLabelMessage() {
        return processedLabelMessage.getReadOnlyProperty();
    }
}
