### About

JavaFX desktop application, which browses audio files, and collects their tags. The collected tags are sent to a kafka topic.

### Docker images
- the docker compose file builds a kafka server

### Run
- Java fx sdk is needed for IntelliJ
- add VM option:
--module-path "path-to-javafx-jdk-lib" --add-modules=javafx.controls,javafx.fxml

### Environment variables

| Name                                      | Format   | Default value                                      | Comment                                                    |
|-------------------------------------------|----------|----------------------------------------------------|------------------------------------------------------------|
| `KAFKA_AUDIO_TAG_TOPIC`                   | string   | audio-tag-created                                  |  |
| `KAFKA_SERVER`                            | string   | localhost:9093                                     |  |
| `GENRE_DELIMITER`                         | string   | ;                                                  | Given multiple genres in tags, the separator between them  |
