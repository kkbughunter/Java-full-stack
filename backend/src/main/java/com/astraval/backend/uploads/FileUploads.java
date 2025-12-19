package com.astraval.backend.uploads;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "file_uploads")
@Data  // Add Lombok for getters/setters
public class FileUploads {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "upload_id")
    private String uploadId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "stored_file_name", nullable = false)  // Fixed typo
    private String storedFileName;

    @Column(name = "file_path", nullable = false)
    private String storedPath;  // only relative path /uploads exampel

    @Column(name = "file_size_bytes")  // Long instead of String
    private Long fileSizeBytes;

    @Column(name = "file_type")
    private String uploadType;

    @Column(name = "mime_type")  // Fixed "mine" typo
    private String mimeType;

    @CreationTimestamp
    @Column(name = "upload_date", nullable = false, updatable = false)
    private LocalDateTime uploadDate;

    @Column(name = "user_id")
    private String userId;

}

