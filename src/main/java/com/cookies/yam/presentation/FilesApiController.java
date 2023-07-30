package com.cookies.yam.presentation;

import com.cookies.yam.application.FilesService;
import com.cookies.yam.application.PostsService;
import com.cookies.yam.domain.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.cookies.yam.util.FileUploadUtil.uploadFile;
@RequiredArgsConstructor
@RequestMapping("/api/vi")
@RestController
public class FilesApiController {
    private final FilesService filesService;
/*
    @PostMapping("/image/upload")
    public void save(@RequestPart("file") MultipartFile file, @RequestPart("posts_id") Long postsId) {
        try {
            String newFilename = uploadFile(file, postsId);

            filesService.save(file.getOriginalFilename(), newFilename, postsId);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

*
    }
    /*
    @PostMapping("/image/path")
    public void loader(@RequestPart("posts_id") Long postsId) {

            Optional<Files> file = filesService.findByPostsId(postsId);
            Files loaderFile = file.orElseGet(Files::new);

            Map<String, Object> map = new HashMap<>();
            map.put("originalName",loaderFile.getOriginalFileName());
            map.put("reName",loaderFile.getRenamedFileName());
            map.put("posts_id", loaderFile.getPostsId());




    }

     */
}