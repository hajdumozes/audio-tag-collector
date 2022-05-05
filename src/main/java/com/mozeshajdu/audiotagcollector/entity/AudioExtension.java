package com.mozeshajdu.audiotagcollector.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum AudioExtension {
    M4A("*.m4a"),
    FLAC("*.flac"),
    MP3("*.mp3"),
    MP4("*.mp4"),
    WAV("*.wav"),
    WMA("*.wma"),
    OGG("*.ogg");

    String filePattern;
}
