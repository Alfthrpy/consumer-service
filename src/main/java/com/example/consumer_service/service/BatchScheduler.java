package com.example.consumer_service.service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchScheduler {

    private final RocksDBService rocksDBService;

    public BatchScheduler(RocksDBService rocksDBService) {
        this.rocksDBService = rocksDBService;
    }

    // Jalan otomatis setiap 60 detik (60000 ms)
    @Scheduled(fixedRate = 60000)
    public void runBatchProcess() {
        System.out.println("Scheduler berjalan: Mengekstrak isi RocksDB ke file...");
        rocksDBService.generateBatchFile();
    }
}