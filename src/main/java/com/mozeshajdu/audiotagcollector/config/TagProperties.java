package com.mozeshajdu.audiotagcollector.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "tag")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagProperties {
    String delimiter;
}
