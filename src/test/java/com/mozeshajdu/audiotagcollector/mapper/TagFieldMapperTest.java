package com.mozeshajdu.audiotagcollector.mapper;

import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.mp4.field.Mp4TagTextField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TagFieldMapperTest {
    public static final String TRACK_TITLE = "track title";
    public static final String ARTIST = "artist";
    public static final String OTHER_ARTIST = "other artist";

    @InjectMocks
    TagFieldMapper underTest;

    @Test
    void shouldJoinWithoutDelimiter_onMappingSingle() {
        // given
        TagField tagField = new Mp4TagTextField(FieldKey.TITLE.name(), TRACK_TITLE);
        List<TagField> tagFields = List.of(tagField);

        // when
        String result = underTest.toSingle(tagFields);

        // then
        assertThat(result).isEqualTo(TRACK_TITLE);
    }

    @Test
    void shouldCollect_onMappingMultiple() {
        // given
        TagField tagField = new Mp4TagTextField(FieldKey.ARTIST.name(), ARTIST);
        TagField otherTagField = new Mp4TagTextField(FieldKey.ARTIST.name(), OTHER_ARTIST);
        List<TagField> tagFields = List.of(tagField, otherTagField);

        // when
        List<String> result = underTest.toMultiple(tagFields);

        // then
        assertThat(result).isEqualTo(List.of(ARTIST, OTHER_ARTIST));
    }
}