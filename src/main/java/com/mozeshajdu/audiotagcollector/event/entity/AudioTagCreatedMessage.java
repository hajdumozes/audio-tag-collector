package com.mozeshajdu.audiotagcollector.event.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AudioTagCreatedMessage {
    String title;
    List<String> artists;
    List<String> albumArtists;
    String album;
    String year;
    String track;
    List<String> genres;
    String grouping;
    Integer rating;
}
