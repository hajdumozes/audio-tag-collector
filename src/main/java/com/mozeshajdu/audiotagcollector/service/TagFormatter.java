package com.mozeshajdu.audiotagcollector.service;

import com.mozeshajdu.audiotagcollector.config.TagDelimiterProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.util.Strings;
import org.jaudiotagger.tag.TagField;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagFormatter {

    public static final String TEXT_TAG_PREFIX = "Text=";
    public static final String TEXT_TAG_QUOTE = "\"";
    public static final String TEXT_TAG_SUFFIX = ";";
    public static final String UNICODE_WHITE_SPACE_CHAR = "\u0000";
    public static final String UNICODE_SPACE_SEPARATOR_CHAR = "\u00A0";
    public static final String SPACE = " ";

    TagDelimiterProperties tagDelimiterProperties;

    public String transformTextTags(TagField tagField) {
        String field = tagField.toString();
        if (field.startsWith(TEXT_TAG_PREFIX)) {
            return field
                    .replace(TEXT_TAG_PREFIX, Strings.EMPTY)
                    .replace(TEXT_TAG_SUFFIX, Strings.EMPTY)
                    .replace(TEXT_TAG_QUOTE, Strings.EMPTY)
                    .replace(UNICODE_WHITE_SPACE_CHAR, tagDelimiterProperties.getGenre())
                    .replace(UNICODE_SPACE_SEPARATOR_CHAR, SPACE)
                    .trim();
        }
        return field;
    }

    public String replaceUnicodeSpaceSeparatorCharacters(String string) {
        return string.replace(UNICODE_SPACE_SEPARATOR_CHAR, SPACE);
    }
}
