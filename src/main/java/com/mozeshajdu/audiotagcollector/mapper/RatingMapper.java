package com.mozeshajdu.audiotagcollector.mapper;

import com.mozeshajdu.audiotagcollector.service.TagFormatter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.util.Strings;
import org.jaudiotagger.tag.TagField;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingMapper {
    public static final String RATING_DELIMITER = ";";
    public static final String RATING_PART_PREFIX = "Rating";
    public static final String ALL_CHARACTERS_BUT_NUMBERS = "[^0-9]";
    public static final Map<String, String> RATING_PAIRS = Map.of(
            "118", "50",
            "128", "60",
            "186", "70",
            "196", "80",
            "242", "90",
            "255", "100");

    TagFormatter tagFormatter;

    @Named("reevaluate")
    public Integer of(List<TagField> ratingStrings) {
        if (ratingStrings.size() > 0) {
            TagField ratingField = ratingStrings.get(0);
            String formattedField = tagFormatter.transformTextTags(ratingField);
            String transformedField = formattedField.contains(RATING_PART_PREFIX) ? transform(formattedField) : formattedField;
            return Integer.parseInt(transformedField);
        } else {
            return null;
        }
    }

    private String transform(String rating) {
        String ratingPart = extractRatingPart(rating);
        String ratingValue = extractRatingValue(ratingPart);
        return fetchStandardValue(ratingValue);
    }

    private String extractRatingPart(String ratingString) {
        String[] parts = ratingString.split(RATING_DELIMITER);
        return Arrays.stream(parts)
                .map(String::trim)
                .filter(part -> part.startsWith(RATING_PART_PREFIX))
                .collect(Collectors.joining());
    }

    private String extractRatingValue(String ratingPart) {
        return ratingPart.replaceAll(ALL_CHARACTERS_BUT_NUMBERS, Strings.EMPTY);
    }

    private String fetchStandardValue(String rating) {
        return RATING_PAIRS.get(rating);
    }
}
