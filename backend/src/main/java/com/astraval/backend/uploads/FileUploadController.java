package com.astraval.backend.uploads;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.astraval.backend.uploads.dto.FileUploadDto;
import com.astraval.backend.uploads.dto.FileWithMetadataDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/file")
public class FileUploadController { 



    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public String uploadSingleFile(@ModelAttribute FileUploadDto dto) throws IOException {
        
        fileUploadService.saveFile(dto);

        return "file upload success...";
    }

    @PostMapping(value = "/upload/files", consumes = "multipart/form-data")
    public ResponseEntity<Map<String, Object>> uploadMultipleFiles(
            @RequestPart("files") List<FileWithMetadataDto> filesWithMetadata) throws IOException {
        
        List<FileUploads> uploads = fileUploadService.saveFilesWithMetadata(filesWithMetadata);
        
        return ResponseEntity.ok(Map.of(
            "success", true,
            "total", uploads.size(),
            "uploadIds", uploads.stream().map(FileUploads::getUploadId).toList()
        ));
    }

    
}
