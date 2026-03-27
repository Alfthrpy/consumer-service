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
        String fileId = extractFileIdFromKey(key);
        String recordId = extractRecordIdFromKey(key);

        FileStateKey newFileStateKey = new FileStateKey(fileId, recordId);
        rocksDBService.save(newFileStateKey, message);

    }

    

    private String extractFileIdFromKey(String key) {
        // Implement logic to extract fileId from the key
        return key.split("-")[0]; // Example: assuming key format is "fileId-recordId"
    }

    private String extractRecordIdFromKey(String key) {
        // Implement logic to extract recordId from the key
        return key.split("-")[1]; // Example: assuming key format is "fileId-recordId"
    }

}
