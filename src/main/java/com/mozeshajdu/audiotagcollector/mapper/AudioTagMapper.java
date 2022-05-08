package com.mozeshajdu.audiotagcollector.mapper;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.entity.TagFieldSelection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TagFieldMapper.class)
public interface AudioTagMapper {

    @Mapping(target = "title", qualifiedByName = "single")
    @Mapping(target = "artists", qualifiedByName = "multiple")
    @Mapping(target = "albumArtists", qualifiedByName = "multiple")
    @Mapping(target = "album", qualifiedByName = "single")
    @Mapping(target = "year", qualifiedByName = "single")
    @Mapping(target = "track", qualifiedByName = "single")
    @Mapping(target = "composer", qualifiedByName = "single")
    @Mapping(target = "genres", qualifiedByName = "multiple")
    @Mapping(target = "grouping", qualifiedByName = "single")
    AudioTag of(TagFieldSelection source);
}