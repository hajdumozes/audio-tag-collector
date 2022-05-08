package com.mozeshajdu.audiotagcollector.ui.config;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfig {

    @Bean
    GridPane gridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(6);
        gridPane.setVgap(6);
        return gridPane;
    }

    @Bean
    Pane pane() {
        Pane rootGroup = new VBox(12);
        rootGroup.setPadding(new Insets(12, 12, 12, 12));
        return rootGroup;
    }
}
