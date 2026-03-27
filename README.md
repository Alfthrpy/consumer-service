# consumer-service-rocksdb



Aplikasi Spring Boot ini membaca pesan Kafka, memproses payload, dan menyimpan state ke RocksDB lokal.

## Teknologi
- Java 21
- Spring Boot 4
- Spring Kafka
- RocksDB JNI
- Lombok

## How to Run
1. Konfigurasi `src/main/resources/application.properties` (Kafka/DB)
2. Jalankan:
   - `./mvnw clean package`
   - `./mvnw spring-boot:run`

## Struktur utama
- `src/main/java/com/example/consumer_service/service` - listener dan service RocksDB
- `src/main/java/com/example/consumer_service/config` - config Kafka
- `src/main/java/com/example/consumer_service/schema` - model state

## Catatan
project ini juga berisi program consumer service terdahulu (pada branch main) dan program consumer service yang sudah diintegrasikan dengan rocksdb (pada branch rocksdb implementation)
