error id: file:///D:/CODING/JAVA/consumer-service/src/main/java/com/example/consumer_service/service/KafkaConsumerListener.java:_empty_/Paths#
file:///D:/CODING/JAVA/consumer-service/src/main/java/com/example/consumer_service/service/KafkaConsumerListener.java
empty definition using pc, found symbol in pc: _empty_/Paths#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 163
uri: file:///D:/CODING/JAVA/consumer-service/src/main/java/com/example/consumer_service/service/KafkaConsumerListener.java
text:
```scala
package com.example.consumer_service.service;

@KafkaListener(topics = "topic-name")
public void consumeBatch(List<String> messages) {

    Path outputPath = @@Paths.get("output.txt");

    try (BufferedWriter writer =
             Files.newBufferedWriter(
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
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/Paths#