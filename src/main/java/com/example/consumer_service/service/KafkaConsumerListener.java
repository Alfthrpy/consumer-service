package com.example.consumer_service.service;
import java.util.List;

import org.rocksdb.RocksDBException;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
    private final FileProcess fileProcess;

    public KafkaConsumerListener(FileProcess fileProcess) {
        this.fileProcess = fileProcess;
    }

    @KafkaListener(topics = "${app.kafka.topic}")
    public void consumeBatch(List<String> messages,@Header(KafkaHeaders.RECEIVED_KEY) List<String> keys) throws RocksDBException {
        for (int i = 0; i < messages.size(); i++) {
            fileProcess.processFile(messages.get(i), keys.get(i));
        }
    }
}
