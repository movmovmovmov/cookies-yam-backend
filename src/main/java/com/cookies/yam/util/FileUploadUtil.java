package com.cookies.yam.util;


import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    public class FileUploadUtil {
        private static final String UPLOAD_DIR = "uploads/";

        public static String uploadFile(MultipartFile file, Long posts_id) throws IOException {
            String fileName = generateFileName(file.getOriginalFilename(), String.valueOf(posts_id));


            Path uploadPath = Paths.get(UPLOAD_DIR);
            /* JDK 오류 */
            //Path uploadPath = Path.of(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try {
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                return fileName;
            } catch (IOException e) {
                throw new IOException("Failed to upload file", e);
            }
        }

        private static String generateFileName(String originalFileName, String posts_id) {
            LocalDateTime now = LocalDateTime.now();
            String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
            String extension = StringUtils.getFilenameExtension(originalFileName);
            return timestamp + "." + extension;
        }
    }

