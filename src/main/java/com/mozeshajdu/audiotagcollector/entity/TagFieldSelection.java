package com.mozeshajdu.audiotagcollector.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.jaudiotagger.tag.TagField;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagFieldSelection {
    List<TagField> title;
    List<TagField> artists;
    List<TagField> albumArtists;
    List<TagField> album;
    List<TagField> year;
    List<TagField> track;
    List<TagField> composer;
    List<TagField> genres;
    List<TagField> grouping;
}
