package com.mozeshajdu.audiotagcollector.service;

import com.mozeshajdu.audiotagcollector.entity.AudioTag;

import java.util.function.Consumer;

public interface KafkaConsumer extends Consumer<AudioTag> {
}
