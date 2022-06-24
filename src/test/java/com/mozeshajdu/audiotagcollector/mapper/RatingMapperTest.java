package com.mozeshajdu.audiotagcollector.mapper;

import com.mozeshajdu.audiotagcollector.service.TagFormatter;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.mp4.field.Mp4TagTextField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RatingMapperTest {
    @InjectMocks
    RatingMapper underTest;

    @Mock
    TagFormatter tagFormatter;

    @Test
    void shouldReturnRating_onMapping_havingStandardRating() {
        // given
        String content = "70";
        TagField ratingField = new Mp4TagTextField(FieldKey.RATING.name(),
                content);
        Mockito.when(tagFormatter.transformTextTags(ratingField)).thenReturn(content);

        // when
        Integer result = underTest.of(List.of(ratingField));

        // then
        assertThat(result).isEqualTo(70);
    }

    @Test
    void shouldStandardizeRating_onMapping_havingOverloadedRatingInfo() {
        // given
        String content = "Email=\"MusicBee\"; Rating=\"186\"; Counter=\"0\"; ";
        TagField ratingField = new Mp4TagTextField(FieldKey.RATING.name(),
                content);
        Mockito.when(tagFormatter.transformTextTags(ratingField)).thenReturn(content);

        // when
        Integer result = underTest.of(List.of(ratingField));

        // then
        assertThat(result).isEqualTo(70);
    }
}