package com.cookies.yam.application.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FilesService {


    @Value("${upload.directory}")
    private String uploadDirectorty;

    @Value("${upload.filename-pattern}")
    private String storeFile(MultipartFile file) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String uniqueFileName = generateUniqueFileName(originalFileName);
        String filePath = uploadDirectorty + File.separator + uniqueFileName;

        // 저장
        file.transferTo(new File(filePath));

        saveFileInfoToDatabase(originalFileName, uniqueFileName);
        return filePath;
    }

    private String generateUniqueFileName(String originalFileName) {
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".");
        String uniqueName = UUID.randomUUID().toString() + extension;
        return uniqueName;
    }

    private void saveFileInfoToDatabase(Stirng originalFileName, String uniqueFileName){

    }
}
