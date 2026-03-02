error id: file:///D:/CODING/JAVA/consumer-service/src/main/java/com/example/consumer_service/service/KafkaConsumerListener.java:_empty_/FileWriterService#
file:///D:/CODING/JAVA/consumer-service/src/main/java/com/example/consumer_service/service/KafkaConsumerListener.java
empty definition using pc, found symbol in pc: _empty_/FileWriterService#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 522
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
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerListener {
@Component
@RequiredArgsConstructor
public class KafkaBatchConsumer {

    private final Fil@@eWriterService fileWriterService;

    @KafkaListener(topics = "${kafka.consumer.topics}")
    public void consumeBatch(List<String> messages) {
        fileWriterService.append(messages);
    }
}
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/FileWriterService#