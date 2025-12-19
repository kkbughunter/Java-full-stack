package com.astraval.backend.uploads.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileUploadDto {
    private MultipartFile file;
    private String userId;
}
