package com.cookies.yam.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vi")

public class ImageUpViewController {

    // 이미지 업로드하고 응답으로 이미지 리소스 전달
    @RequestMapping(value="/images/site/{file}/loader.do")
    public @ResponseBody byte[] siteImageLoader(
            HttpServletResponse response,
            @PathVariable(value="file") String file
    ) throws Exception{

        //파일 경로 세팅 필요
        String fileFullPath = StaticData.getInstance().getAttachPath();
        fileFullPath += file;

        File image = new File(fileFullPath);
        if(image.exists()) return IOUtils.toByteArray(new FileInputStream(image));
        else return null;
    }
}
