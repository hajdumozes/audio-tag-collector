package com.mozeshajdu.audiotagcollector.mapper;

import com.mozeshajdu.audiotagcollector.config.TagProperties;
import com.mozeshajdu.audiotagcollector.service.TagFormatter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jaudiotagger.tag.TagField;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagFieldMapper {
    TagFormatter tagFormatter;
    TagProperties tagProperties;

    @Named("single")
    public String toSingle(List<TagField> tagFields) {
        return tagFields.stream()
                .map(tagFormatter::transformTextTags)
                .map(tagFormatter::replaceUnicodeSpaceSeparatorCharacters)
                .collect(Collectors.joining());
    }

    @Named("multiple")
    public List<String> toMultiple(List<TagField> genreStrings) {
        return genreStrings.stream()
                .map(tagFormatter::transformTextTags)
                .map(tagFormatter::replaceUnicodeSpaceSeparatorCharacters)
                .map(this::split)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<String> split(String genreString) {
        return Arrays.stream(genreString.split(tagProperties.getDelimiter())).map(String::trim).collect(Collectors.toList());
    }
}
