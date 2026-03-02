package com.example.consumer_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class KafkaConsumerProperties {
    private String bootstrapServers = "localhost:9092";
    private String groupId = "my-group";
    private String enableAutoCommit = "false";
    private String autoOffsetReset = "earliest";
    private String maxPoolRecords = "100";
    private String FetchMinBytes = "1";
    private String FetchMaxWaitMs = "500";


    // Getters and Setters

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getEnableAutoCommit() {
        return enableAutoCommit;
    }

    public String getAutoOffsetReset() {
        return autoOffsetReset;
    }

    public String getMaxPoolRecords() {
        return maxPoolRecords;
    }

    public String getFetchMinBytes() {
        return FetchMinBytes;
    }

    public String getFetchMaxWaitMs() {
        return FetchMaxWaitMs;
    }



}
