### About

JavaFX desktop application, which browses audio files, and collects their tags. The collected tags are sent to a kafka topic.

### Docker images
- the docker compose file builds a kafka server

### Run
- Java fx sdk is needed for IntelliJ
- add VM option:
--module-path "path-to-javafx-jdk-lib" --add-modules=javafx.controls,javafx.fxml
- for yml support also add to VM option: -Dspring.config.location=src/main/resources/application.yml

### Environment variables

| Name                                      | Format   | Default value                                      | Comment                                                    |
|-------------------------------------------|----------|----------------------------------------------------|------------------------------------------------------------|
| `KAFKA_AUDIO_TAG_CREATED_TOPIC`           | string   | audio-tag-created                                  |  |
| `KAFKA_AUDIO_TAG_PROCESSED_TOPIC`         | string   | audio-tag-processed                                |  |
| `KAFKA_AUDIO_TAG_ADDED_TOPIC`             | string   | audio-tag-added                                    |  |
| `KAFKA_SERVER`                            | string   | localhost:9093                                     |  |
| `TAG_DELIMITER`                           | string   | ;                                                  | Given multiple genres in tags, the separator between them  |
