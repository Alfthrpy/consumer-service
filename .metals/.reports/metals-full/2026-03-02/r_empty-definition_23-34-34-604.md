error id: file:///D:/CODING/JAVA/consumer-service/src/main/java/com/example/consumer_service/service/KafkaConsumerListener.java:java/nio/file/Files#newBufferedWriter(+1).
file:///D:/CODING/JAVA/consumer-service/src/main/java/com/example/consumer_service/service/KafkaConsumerListener.java
empty definition using pc, found symbol in pc: java/nio/file/Files#newBufferedWriter(+1).
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 569
uri: file:///D:/CODING/JAVA/consumer-service/src/main/java/com/example/consumer_service/service/KafkaConsumerListener.java
text:
```scala
package com.example.consumer_service.service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumerListener {
    @KafkaListener(topics = "topic-name")
    public void consumeBatch(List<String> messages) {

        Path outputPath = Paths.get("output.txt");

        try (BufferedWriter writer = Files.newBuffe@@redWriter(
                outputPath,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND)) {

            for (String message : messages) {
                writer.write(message);
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: java/nio/file/Files#newBufferedWriter(+1).