package com.mozeshajdu.audiotagcollector.view.audiocollector;

import com.mozeshajdu.audiotagcollector.service.AudioFileReader;
import de.saxsys.mvvmfx.FxmlView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AudioCollectorView implements FxmlView<AudioCollectorViewModel> {

    @FXML
    Pane dragAndDropPane;

    @FXML
    Label processedCounter;

    final AudioCollectorViewModel audioCollectorViewModel;

    final AudioFileReader audioFileReader;

    public void initialize() {
        dragAndDropPane.setOnDragOver(event -> event.acceptTransferModes(TransferMode.MOVE));

        dragAndDropPane.setOnDragDropped(this::processFiles);

        processedCounter.textProperty().bind(audioCollectorViewModel.getProcessedLabelMessage());
    }

    private void processFiles(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasFiles()) {
            audioCollectorViewModel.resetProcessed();
            audioCollectorViewModel.setGoal(dragboard.getFiles().size());
            dragboard.getFiles().forEach(audioFileReader::read);
        }
    }
}
