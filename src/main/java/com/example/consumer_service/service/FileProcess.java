package com.example.consumer_service.service;

import org.rocksdb.RocksDBException;
import org.springframework.stereotype.Component;

import com.example.consumer_service.schema.FileStateKey;

@Component
public class FileProcess {
    private final RocksDBService rocksDBService;

    public FileProcess(RocksDBService rocksDBService) {
        this.rocksDBService = rocksDBService;
    }

    public void processFile(String message, String key) throws RocksDBException{
        String recordId = extractRecordIdFromMessage(message);

        FileStateKey newFileStateKey = new FileStateKey(key, recordId);
        rocksDBService.save(newFileStateKey, message);

    }

    


    private String extractRecordIdFromMessage(String message) {
        String[] parts = message.split(",");
        return parts[0]; // Asumsikan recordId ada di bagian pertama

    }

}
