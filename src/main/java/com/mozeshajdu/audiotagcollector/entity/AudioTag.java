package com.mozeshajdu.audiotagcollector.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AudioTag {
    String title;
    List<String> artists;
    List<String> albumArtists;
    String album;
    String year;
    String track;
    String composer;
    List<String> genres;
    String grouping;
}

