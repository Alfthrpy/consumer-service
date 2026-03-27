package com.example.consumer_service.service;

import org.rocksdb.ColumnFamilyDescriptor;
import org.rocksdb.ColumnFamilyHandle;
import org.rocksdb.DBOptions;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;
import org.rocksdb.RocksIterator;
import org.rocksdb.WriteBatch;
import org.rocksdb.WriteOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.consumer_service.schema.FileStateKey;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class RocksDBService {

    @Value("${app.file.db-path}")
    private String DB_PATH;
    private RocksDB db;

    private ColumnFamilyHandle dataHandle;
    private ColumnFamilyHandle metaHandle;

    @PostConstruct
    public void init() throws RocksDBException {
        RocksDB.loadLibrary();

        try (DBOptions dbOptions = new DBOptions().setCreateIfMissing(true).setCreateMissingColumnFamilies(true)) {

            List<ColumnFamilyDescriptor> cfDescriptors = Arrays.asList(
                    new ColumnFamilyDescriptor(RocksDB.DEFAULT_COLUMN_FAMILY),
                    new ColumnFamilyDescriptor("meta-data".getBytes()),
                    new ColumnFamilyDescriptor("csv-data".getBytes()));

            List<ColumnFamilyHandle> cfHandles = new ArrayList<>();
            this.db = RocksDB.open(dbOptions, DB_PATH, cfDescriptors, cfHandles);

            this.metaHandle = cfHandles.get(1);
            this.dataHandle = cfHandles.get(2);
        }
    }

    public void save(FileStateKey key, String value) throws RocksDBException {
        db.put(dataHandle, key.toBytes(), value.getBytes());
    }

    public String find(String key) throws RocksDBException {
        byte[] bytes = db.get(key.getBytes());
        return bytes != null ? new String(bytes) : null;
    }

    public void saveInBatch(Map<String, String> dataBatch) throws RocksDBException {

        try (WriteBatch batch = new WriteBatch();
                WriteOptions writeOptions = new WriteOptions()) {

            for (Map.Entry<String, String> entry : dataBatch.entrySet()) {
                batch.put(dataHandle, entry.getKey().getBytes(), entry.getValue().getBytes());
            }

            db.write(writeOptions, batch);
        }
    }

    public String getMetadata(String fileName) throws RocksDBException {
        byte[] status = db.get(metaHandle, ("META:" + fileName).getBytes());
        return status != null ? new String(status) : null;
    }

    public void saveMetadata(String fileName, String status, String fileId) throws RocksDBException {
        if (status.equals("COMPLETED")) {
            String dateNow = LocalDate.now().toString();
            String value = "COMPLETED|" + dateNow + "|" + fileId;
            db.put(metaHandle, ("META:" + fileName).getBytes(), value.getBytes());
        } else {
            db.put(metaHandle, ("META:" + fileName).getBytes(), status.getBytes());
        }
    }

    public void generateBatchFile() {
        String fileName = "batch_output_" + System.currentTimeMillis() + ".csv";
        File outputFile = new File(fileName);

        try (RocksIterator iterator = db.newIterator();
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            iterator.seekToFirst();

            System.out.println("Memulai proses tulis ke file: " + fileName);
            int count = 0;

            while (iterator.isValid()) {
                byte[] keyBytes = iterator.key();
                byte[] valueBytes = iterator.value();


                String key = new String(keyBytes, StandardCharsets.UTF_8);
                String value = new String(valueBytes, StandardCharsets.UTF_8);

                writer.write(key + "," + value);
                writer.newLine();

                db.delete(keyBytes);

                count++;
                iterator.next();
            }

            System.out.println("Selesai! Berhasil menulis " + count + " baris ke file.");

        } catch (Exception e) {
            System.err.println("Gagal men-generate file: " + e.getMessage());
        }
    }

    public void cleanupOldData(int daysThresold) throws RocksDBException {
        LocalDate limitDate = LocalDate.now().minusDays(daysThresold);

        try (RocksIterator iter = db.newIterator(metaHandle)) {
            for (iter.seekToFirst(); iter.isValid(); iter.next()) {
                String fileName = new String(iter.key());
                String metaValue = new String(iter.value()); // Format: COMPLETED|2025-05-20|uuid-123

                String[] parts = metaValue.split("\\|");
                if (parts.length == 3 && parts[0].equals("COMPLETED")) {
                    LocalDate processedDate = LocalDate.parse(parts[1]);
                    String fileId = parts[2];

                    // Jika tanggal proses lebih lama atau sama dengan dari batas (limitDate)
                    if (!processedDate.isAfter(limitDate)) {
                        System.out.println("Cleaning up old data for file: " + fileName);

                        // 1. Hapus isi baris CSV di CF data
                        String startKey = fileId + ":";
                        String endKey = fileId + ":\u00ff";
                        db.deleteRange(dataHandle, startKey.getBytes(), endKey.getBytes());

                        // 2. Update metadata (opsional): Tandai bahwa data mentahnya sudah dihapus
                        String updatedMeta = "ARCHIVED|" + parts[1] + "|" + fileId;
                        db.put(metaHandle, fileName.getBytes(), updatedMeta.getBytes());
                    }
                }
            }
        }
    }

    @PreDestroy
    public void close() {
        if (db != null) {
            db.close();
            System.out.println("RocksDB Service Closed safely.");
        }
    }

    public String countEntriesWithPrefix(String fileId) {
        int count = 0;
        String prefix = fileId + ":";
        try (RocksIterator iter = db.newIterator(dataHandle)) {
            for (iter.seek(prefix.getBytes()); iter.isValid(); iter.next()) {
                String key = new String(iter.key());
                if (!key.startsWith(prefix)) {
                    break; // Keluar jika sudah melewati prefix
                }
                count++;
            }
        }
        return String.valueOf(count);
    }

}