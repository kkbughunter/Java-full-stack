package com.astraval.backend.uploads;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUploads, String> {

    
}
