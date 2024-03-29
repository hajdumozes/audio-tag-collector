package com.mozeshajdu.audiotagcollector.view.audiocollector;

import com.mozeshajdu.audiotagcollector.entity.AudioExtension;
import com.mozeshajdu.audiotagcollector.service.AudioFileReader;
import de.saxsys.mvvmfx.FxmlView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AudioCollectorView implements FxmlView<AudioCollectorViewModel> {

    @FXML
    Pane dragAndDropPane;

    @FXML
    Label processedCounter;

    @FXML
    Label addedCounter;

    @FXML
    ProgressBar progressBar;

    final AudioCollectorViewModel audioCollectorViewModel;

    final AudioFileReader audioFileReader;

    public void initialize() {
        dragAndDropPane.setOnDragOver(event -> event.acceptTransferModes(TransferMode.MOVE));

        dragAndDropPane.setOnDragDropped(this::processFiles);

        processedCounter.textProperty().bind(audioCollectorViewModel.getProcessedLabelMessage());

        addedCounter.textProperty().bind(audioCollectorViewModel.getAddedLabelMessage());

        progressBar.progressProperty().bind(audioCollectorViewModel.getProgress());
    }

    private void processFiles(DragEvent event) {
        Dragboard dragboard = event.getDragboard();
        if (dragboard.hasFiles()) {
            progressBar.setVisible(true);
            audioCollectorViewModel.resetProcessed();
            List<File> audioFiles = dragboard.getFiles().stream().filter(this::isAudioFile).toList();
            audioCollectorViewModel.setGoal(audioFiles.size());
            audioCollectorViewModel.setProgressBarUnit(1 / (double) audioFiles.size());
            audioFiles.forEach(audioFileReader::read);
        }
    }

    private List<String> getAllAudioExtensionPattern() {
        AudioExtension[] values = AudioExtension.values();
        return Arrays.stream(values).map(AudioExtension::getExtension).toList();
    }

    private boolean isAudioFile(File file) {
        String fileExtension = getFileExtension(file.getName()).orElse(Strings.EMPTY);
        return getAllAudioExtensionPattern().contains(fileExtension);
    }

    private Optional<String> getFileExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(name -> name.contains("."))
                .map(name -> name.substring(filename.lastIndexOf(".")));
    }
}
