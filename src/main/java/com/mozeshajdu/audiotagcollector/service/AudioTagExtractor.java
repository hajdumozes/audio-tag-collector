package com.mozeshajdu.audiotagcollector.service;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.entity.TagFieldSelection;
import com.mozeshajdu.audiotagcollector.mapper.AudioTagMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AudioTagExtractor {
    public static final String FIELD_KEY_CONTENT_GROUP = "CONTENTGROUP";

    AudioTagMapper audioTagMapper;

    public AudioTag extract(Tag tag) {
        TagFieldSelection tagFieldSelection = TagFieldSelection.builder()
                .title(tag.getFields(FieldKey.TITLE))
                .artists(tag.getFields(FieldKey.ARTIST))
                .albumArtists(tag.getFields(FieldKey.ALBUM_ARTIST))
                .album(tag.getFields(FieldKey.ALBUM))
                .year(tag.getFields(FieldKey.YEAR))
                .track(tag.getFields(FieldKey.TRACK))
                .composer(tag.getFields(FieldKey.COMPOSER))
                .genres(tag.getFields(FieldKey.GENRE))
                .grouping(tag.getFields(FIELD_KEY_CONTENT_GROUP))
                .build();
        return audioTagMapper.of(tagFieldSelection);
    }
}
