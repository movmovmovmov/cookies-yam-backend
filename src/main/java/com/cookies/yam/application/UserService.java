package com.cookies.yam.application;

import com.cookies.yam.application.dto.PostsDto;
import com.cookies.yam.domain.Address;
import com.cookies.yam.domain.Category;
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
import java.util.Optional;

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
        String nickname = dto.getNickname();
        Address address = dto.getAddress();
        Category category1 = dto.getCategory1();
        Category category2 = dto.getCategory2();
        Category category3 = dto.getCategory3();
        Category category4 = dto.getCategory4();
        Category category5 = dto.getCategory5();
        user.modify(nickname, encPassword, address, category1, category2, category3, category4, category5);
    }


    @Transactional
    public Optional<User> findByUsername(String user_name) {
        Optional<User> user = userRepository.findByUsername(user_name);

        return user;
    }

}
