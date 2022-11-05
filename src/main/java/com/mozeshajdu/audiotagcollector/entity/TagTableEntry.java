package com.mozeshajdu.audiotagcollector.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagTableEntry {
    String title;
    String album;
    @Builder.Default
    @EqualsAndHashCode.Exclude
    ProcessingStatus processingStatus = ProcessingStatus.PENDING;
    @EqualsAndHashCode.Exclude
    String fileName;
}
