package com.mozeshajdu.audiotagcollector.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AudioTag {
    String title;
    List<String> artists;
    List<String> albumArtists;
    String album;
    String year;
    String track;
    List<String> genres;
    String grouping;
    Integer rating;
    @Builder.Default
    @EqualsAndHashCode.Exclude
    ProcessingStatus processingStatus = ProcessingStatus.PENDING;
}

