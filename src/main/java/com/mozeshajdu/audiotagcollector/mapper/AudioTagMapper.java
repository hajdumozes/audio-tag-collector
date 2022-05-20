package com.mozeshajdu.audiotagcollector.mapper;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;
import com.mozeshajdu.audiotagcollector.entity.TagFieldSelection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TagFieldMapper.class, GenreMapper.class, RatingMapper.class})
public interface AudioTagMapper {

    @Mapping(target = "title", qualifiedByName = "single")
    @Mapping(target = "artists", qualifiedByName = "multiple")
    @Mapping(target = "albumArtists", qualifiedByName = "multiple")
    @Mapping(target = "album", qualifiedByName = "single")
    @Mapping(target = "year", qualifiedByName = "single")
    @Mapping(target = "track", qualifiedByName = "single")
    @Mapping(target = "genres", qualifiedByName = "split")
    @Mapping(target = "grouping", qualifiedByName = "single")
    @Mapping(target = "rating", qualifiedByName = "reevaluate")
    AudioTag of(TagFieldSelection source);
}
