package com.astraval.backend.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUtils {
    

     public String saveFileToServer(MultipartFile file, String path) throws IOException{
        /*
            * get file and file path save to server
            * if success return stored file name
            * else return null
        */
        if(file == null || file.isEmpty()) 
            return null;
        
        Path uploadPath = Paths.get(path);
        if(!Files.exists(uploadPath)) 
            Files.createDirectories(uploadPath);

        String storedName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        
        Path filePath = uploadPath.resolve(storedName);
        Files.copy(file.getInputStream(), filePath);

        return storedName;
    }
}
