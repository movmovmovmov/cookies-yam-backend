package com.cookies.yam.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vi")

public class ImageUploadViewController {

    // 이미지 저장


    // 이미지 리소스 전달
    @GetMapping(value="/images/site/{file}/loader.do")
    public @ResponseBody byte[] siteImageViewLoader(
            HttpServletResponse response,
            @PathVariable(value="file") String file
    ) throws Exception{

        //WEB 파일 경로 세팅 필요
        String fileFullPath = StaticData.getInstance().getAttachPath();
        fileFullPath += file;

        File image = new File(fileFullPath);
        if(image.exists()) return IOUtils.toByteArray(new FileInputStream(image));
        else return null;
    }
}
