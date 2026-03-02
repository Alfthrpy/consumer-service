package com.example.consumer_service.service;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
    private final FileWriterService fileWriterService;

    public KafkaConsumerListener(FileWriterService fileWriterService) {
        this.fileWriterService = fileWriterService;
    }

    @KafkaListener(topics = "${app.kafka.topic}")
    public void consumeBatch(List<String> messages) {
        fileWriterService.append(messages);
    }
}
