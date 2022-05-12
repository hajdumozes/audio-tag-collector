package com.mozeshajdu.audiotagcollector.service;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.entity.TagFieldSelection;
import com.mozeshajdu.audiotagcollector.mapper.AudioTagMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AudioTagExtractor {
    public static final List<String> FIELD_KEY_CONTENT_GROUP = List.of("CONTENTGROUP", "TIT1", "©grp");

    AudioTagMapper audioTagMapper;

    public AudioTag extract(Tag tag) {
        TagFieldSelection tagFieldSelection = TagFieldSelection.builder()
                .title(tag.getFields(FieldKey.TITLE))
                .artists(tag.getFields(FieldKey.ARTIST))
                .albumArtists(tag.getFields(FieldKey.ALBUM_ARTIST))
                .album(tag.getFields(FieldKey.ALBUM))
                .year(tag.getFields(FieldKey.YEAR))
                .track(tag.getFields(FieldKey.TRACK))
                .genres(tag.getFields(FieldKey.GENRE))
                .grouping(getGrouping(tag))
                .rating(tag.getFields(FieldKey.RATING))
                .build();
        return audioTagMapper.of(tagFieldSelection);
    }

    private List<TagField> getGrouping(Tag tag) {
        return FIELD_KEY_CONTENT_GROUP.stream()
                .map(tag::getFields)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
