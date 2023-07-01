package com.cookies.yam.application;

import com.cookies.yam.application.dto.FilesDto;
import com.cookies.yam.application.dto.PostsDto;
import com.cookies.yam.domain.Address;
import com.cookies.yam.domain.Files;
import com.cookies.yam.domain.Posts;
import com.cookies.yam.domain.User;
import com.cookies.yam.infrastructure.persistence.AddressRepository;
import com.cookies.yam.infrastructure.persistence.FilesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class FilesService {

    private final FilesRepository filesRepository;


    @Transactional
    public Optional<Files> findByPostsId(Long postsId) {

         Optional<Files> files = filesRepository.findByPostsId(postsId);
        return files;
    }
    @Transactional
    public Long save(String originalFileName, String renamedFileName, Long posts_id ) {

        FilesDto.Request dto = new FilesDto.Request();
        dto.setOriginalFileName(originalFileName);
        dto.setRenamedFileName(renamedFileName);
        dto.setPostsId(posts_id);
        Files files = dto.toEntity();
        filesRepository.save(files);

        return files.getId();
    }

}
