package com.example.consumer_service.schema;

import java.nio.charset.StandardCharsets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileStateKey {

    private String fileId;

    private String recordId; 

    public byte[] toBytes() {
        // Menggabungkan key dengan delimiter "|"
        String compositeKey = this.fileId + "|" + this.recordId;
        
        // Sangat disarankan untuk selalu eksplisit menggunakan UTF_8 
        // agar tidak terjadi bug jika aplikasi dijalankan di OS yang berbeda
        return compositeKey.getBytes(StandardCharsets.UTF_8);
    }

}