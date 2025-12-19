package com.astraval.backend.uploads;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.astraval.backend.uploads.dto.FileUploadDto;
import com.astraval.backend.uploads.dto.FileWithMetadataDto;
import com.astraval.backend.utils.FileUtils;

@Service    
public class FileUploadService {

    @Value("${upload.path}")
    private String uploadDir;   

    @Autowired
    private FileUtils fileUtils;

    @Autowired
    FileUploadRepository fileUploadRepository;
    

    public FileUploads saveFile(FileUploadDto dto) throws IOException {

        MultipartFile userFile = dto.getFile();
        String storedFileName = fileUtils.saveFileToServer(userFile, uploadDir);

        // prepare insert query data
        String fileName = userFile.getOriginalFilename();      // "document.pdf"
        Long fileSizeBytes = userFile.getSize();               // 1024000 (bytes)
        String mimeType = userFile.getContentType();           // "application/pdf"

        // Complete mapping to entity
        FileUploads upload = new FileUploads();
        upload.setFileName(fileName);
        upload.setFileSizeBytes(fileSizeBytes);
        upload.setMimeType(mimeType);
        upload.setUploadType("Test Upload File");
        upload.setStoredPath(uploadDir + storedFileName);
        upload.setUserId(dto.getUserId());
        upload.setStoredFileName(storedFileName);

        return fileUploadRepository.save(upload);
    }

    public List<FileUploads> saveFilesWithMetadata(List<FileWithMetadataDto> filesWithMetadata) throws IOException {

        return filesWithMetadata.stream()
         .filter(dto -> dto.getUserId() != null && !dto.getFile().isEmpty())
        .map(dto -> {
           try {
            // call the single file save function so that we need single file save dto also.
            FileUploadDto singleFile = new FileUploadDto();
            singleFile.setFile(dto.getFile());
            singleFile.setUserId(dto.getUserId());
            return saveFile(singleFile);

            } catch (Exception e) {
                 throw new RuntimeException(
                        "Failed to save file: " + dto.getFile().getOriginalFilename(), e);
            }
        })
        .toList();
    }

}
