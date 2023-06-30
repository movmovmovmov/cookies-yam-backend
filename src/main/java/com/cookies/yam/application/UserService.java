package com.cookies.yam.application;

import com.cookies.yam.application.dto.PostsDto;
import com.cookies.yam.domain.Address;
import com.cookies.yam.domain.Category;
import com.cookies.yam.domain.Posts;
import com.cookies.yam.domain.User;
import com.cookies.yam.infrastructure.persistence.UserRepository;
import com.cookies.yam.application.dto.UserDto;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
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

    //private final BCryptPasswordEncoder encoder;

    /* 회원가입 */
    @Transactional
    public void userJoin(UserDto.Request dto) {

        //dto.setPassword(encoder.encode(dto.getPassword()));

        User user = userRepository.save(dto.toEntity());
        user.getUsername();
    }

    /* 회원가입 시, 아이디 중복 체크*/
    @Transactional
    public boolean existsByUsername(String username){
        boolean result;
        result = userRepository.existsByUsername(username);
        return result;
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

    /* 회원정보(비밀번호) 수정 */
    @Transactional
    public void modify(UserDto.Request dto) {
        User user = userRepository.findByUsername(dto.toEntity().getUsername()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        //String encPassword = encoder.encode(dto.getPassword());
        String password = dto.getPassword();
        user.modify(password);
    }

    /* 회원 동네 정보 입력 */
    public void addressModify(String username, Long addressId){
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        Address address = entityManager.find(Address.class, addressId);

        Long address_id = dto.getAddress_id();
        user.addressModify(address_id);
    }

    /* 회원 닉네임 변경 */
    public void nicknameModify(UserDto.Request dto) {
        User user = userRepository.findByUsername(dto.toEntity().getUsername()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        String nickname = dto.getNickname();
        user.nicknameModify(nickname);
    }


    @Transactional
    public Optional<User> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        return user;
    }

    @Transactional
    public User findById(int id) {

         User user = userRepository.findById(id);
        return user;
    }


}
