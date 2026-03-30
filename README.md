# Consumer Service with RocksDB

A Spring Boot application that consumes Kafka messages, processes payloads, and persists state to local RocksDB.

## Technologies
- Java 21
- Spring Boot 3
- Spring Kafka
- RocksDB JNI
- Lombok

## How to Run
1. Configure `src/main/resources/application.properties` (Kafka and DB settings)
2. Run:
   - `./mvnw clean package`
   - `./mvnw spring-boot:run`

## Main Structure
- `service/` - Kafka listener and RocksDB service
- `config/` - Kafka configuration
- `schema/` - State models

## Notes
This project contains the previous consumer service (main branch) and the RocksDB-integrated version (rocksdb-implementation branch).
