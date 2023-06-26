package com.cookies.yam.application;

import com.cookies.yam.application.dto.PostsDto;
import com.cookies.yam.domain.Posts;
import com.cookies.yam.domain.User;
import com.cookies.yam.infrastructure.persistence.UserRepository;
import com.cookies.yam.application.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    /* 회원가입 */
    @Transactional
    public void userJoin(UserDto.Request dto) {

        dto.setPassword(encoder.encode(dto.getPassword()));

        userRepository.save(dto.toEntity());
    }

    /* 회원가입 시, 유효성 검사 및 중복 체크 */
    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 검사, 중복 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    /* 회원수정 (dirty checking) */
    @Transactional
    public void modify(UserDto.Request dto) {
        User user = userRepository.findById(dto.toEntity().getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        String encPassword = encoder.encode(dto.getPassword());
        user.modify(encPassword);
    }


    /* READ 게시글 리스트 조회 readOnly 속성으로 조회속도 개선 */
    @Transactional
    public String findByUserName(String user_name) {
        Posts posts = userRepository.findByUsername(user_name).orElseThrow(() ->
                final String result = "N");

        return result;
    }
}
