package com.mozeshajdu.audiotagcollector.mapper;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.entity.TagFieldSelection;
import com.mozeshajdu.audiotagcollector.entity.TagTableEntry;
import com.mozeshajdu.audiotagcollector.event.entity.AudioTagAddedMessage;
import com.mozeshajdu.audiotagcollector.event.entity.AudioTagCreatedMessage;
import com.mozeshajdu.audiotagcollector.event.entity.AudioTagProcessedMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TagFieldMapper.class, RatingMapper.class})
public interface AudioTagMapper {

    @Mapping(target = "processingStatus", ignore = true)
    @Mapping(target = "title", qualifiedByName = "single")
    @Mapping(target = "artists", qualifiedByName = "multiple")
    @Mapping(target = "albumArtists", qualifiedByName = "multiple")
    @Mapping(target = "album", qualifiedByName = "single")
    @Mapping(target = "year", qualifiedByName = "single")
    @Mapping(target = "track", qualifiedByName = "single")
    @Mapping(target = "genres", qualifiedByName = "multiple")
    @Mapping(target = "grouping", qualifiedByName = "single")
    @Mapping(target = "rating", qualifiedByName = "reevaluate")
    AudioTag of(TagFieldSelection source);

    AudioTagCreatedMessage of(AudioTag source);

    @Mapping(target = "processingStatus", ignore = true)
    AudioTag of(AudioTagProcessedMessage source);

    @Mapping(target = "processingStatus", ignore = true)
    AudioTag of(AudioTagAddedMessage source);

    TagTableEntry ofTag(AudioTag source);

    @Mapping(target = "processingStatus", ignore = true)
    TagTableEntry ofProcessedMessage(AudioTagProcessedMessage source);

    @Mapping(target = "processingStatus", ignore = true)
    TagTableEntry ofAddedMessage(AudioTagAddedMessage source);
}
