### About

JavaFX desktop application, which browses audio files, and collects their tags. The collected tags are sent to a kafka topic.

### Environment variables

| Name                                      | Format   | Default value                                      | Comment                                                    |
|-------------------------------------------|----------|----------------------------------------------------|------------------------------------------------------------|
| `KAFKA_AUDIO_TAG_TOPIC`                   | string   |                                                    |  |
| `KAFKA_SERVER`                            | string   |                                                    |  |
| `GENRE_DELIMITER`                         | string   |                                                    | Given multiple genres in tags, the separator between them  |
