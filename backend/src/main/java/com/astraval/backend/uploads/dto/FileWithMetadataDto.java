package com.astraval.backend.uploads.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileWithMetadataDto {
    private String userId;           // Custom ID
    private MultipartFile file;      // File itself
}
