package com.mozeshajdu.audiotagcollector.service;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.entity.TagFieldSelection;
import com.mozeshajdu.audiotagcollector.mapper.AudioTagMapper;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.mp4.Mp4Tag;
import org.jaudiotagger.tag.mp4.field.Mp4TagTextField;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.mozeshajdu.audiotagcollector.service.AudioTagExtractor.FIELD_KEY_CONTENT_GROUP;

@ExtendWith(MockitoExtension.class)
class AudioTagExtractorTest {

    public static final String TAG_TITLE = "Bowerstone Market";
    public static final String TAG_ARTIST = "Russell Shaw";
    public static final String TAG_ALBUM_ARTIST = "Danny Elfman";
    public static final String TAG_ALBUM = "Fable 2";
    public static final String TAG_YEAR = "2008";
    public static final String TAG_TRACK = "9";
    public static final String TAG_GENRE = "Game Score";
    public static final String TAG_GROUPING = "Mild - vivid, #tranquility";
    @InjectMocks
    AudioTagExtractor underTest;

    @Mock
    AudioTagMapper audioTagMapper;

    @Test
    void shouldCallMapper_onExtracting() {
        // given
        Mp4Tag mp4Tag = createTag();
        TagFieldSelection tagFieldSelection = toTagFieldSelection(mp4Tag);

        // when
        underTest.extract(mp4Tag);

        // then
        Mockito.verify(audioTagMapper).of(tagFieldSelection);
    }

    @SneakyThrows
    private Mp4Tag createTag() {
        Mp4Tag mp4Tag = new Mp4Tag();
        mp4Tag.addField(FieldKey.TITLE, TAG_TITLE);
        mp4Tag.addField(FieldKey.ARTIST, TAG_ARTIST);
        mp4Tag.addField(FieldKey.ALBUM_ARTIST, TAG_ALBUM_ARTIST, TAG_ARTIST);
        mp4Tag.addField(FieldKey.ALBUM, TAG_ALBUM);
        mp4Tag.addField(FieldKey.YEAR, TAG_YEAR);
        mp4Tag.addField(FieldKey.TRACK, TAG_TRACK);
        mp4Tag.addField(FieldKey.COMPOSER, Strings.EMPTY);
        mp4Tag.addField(FieldKey.GENRE, TAG_GENRE);
        TagField groupingField = new Mp4TagTextField(FIELD_KEY_CONTENT_GROUP, TAG_GROUPING);
        mp4Tag.addField(groupingField);
        return mp4Tag;
    }

    private TagFieldSelection toTagFieldSelection(Tag tag) {
        return TagFieldSelection.builder()
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
    }
}