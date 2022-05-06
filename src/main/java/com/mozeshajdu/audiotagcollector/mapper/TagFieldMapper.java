package com.mozeshajdu.audiotagcollector.mapper;

import org.jaudiotagger.tag.TagField;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagFieldMapper {

    @Named("single")
    public String toSingle(List<TagField> tagFields) {
        return tagFields.stream().map(TagField::toString).collect(Collectors.joining());
    }

    @Named("multiple")
    public List<String> toMultiple(List<TagField> tagFields) {
        return tagFields.stream().map(TagField::toString).collect(Collectors.toList());
    }
}
