package com.example.consumer_service.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class FileWriterService {

    private BufferedWriter writer;

    @PostConstruct
    public void init() throws IOException {
        writer = Files.newBufferedWriter(
                Paths.get("output.txt"),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    public synchronized void append(List<String> messages) {
        try {
            for (String message : messages) {
                writer.write(message);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}