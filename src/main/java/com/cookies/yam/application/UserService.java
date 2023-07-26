package com.cookies.yam.application;


import com.cookies.yam.domain.User;
import com.cookies.yam.infrastructure.persistence.UserRepository;
import com.cookies.yam.application.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service

public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }
    @Transactional
    public User join(UserDto.Request dto) {

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = userRepository.save(dto.toEntity());
        return user;
    }

    @Transactional
    public Map login(String username, String password){
        Optional<User> optUser = userRepository.findByUsername(username);
        Map result = new HashMap<>();
        String status;
        if(optUser.isPresent()) {
            User user = optUser.get();
            if(passwordEncoder.matches(password, user.getPassword())){
                status = "Login Success";

            } else {
                status = "Not Matches";
            }
            result.put("status", status);
            result.put("user", user);

        } else {
            status = "No User";
            result.put("status", status);
        }


        return result;
    }

    /* 회원가입 시, 아이디 중복 체크*/
    @Transactional
    public boolean existsByUsername(String username){
        boolean result;
        result = userRepository.existsByUsername(username);
        return result;
    }

    /* 회원가입 시, 유효성 검사 및 중복 체크 */
    /* 회원정보(비밀번호) 수정 */
    @Transactional
    public void modify(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));


        user.modify(password);
    }

    /* 회원 동네 정보 입력 */
    public User addressModify(String username, Long addressId){
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        user.addressModify(addressId);
        User response = userRepository.save(user);
        return response;
    }

    /* 회원 닉네임 변경 */
    public User nicknameModify(String username, String nickname) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.nicknameModify(nickname);
        User response = userRepository.save(user);
        return response;
    }

    @Transactional
    public Optional<User> category1Modify(String username, Long category1) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.category1Modify(category1);
        userRepository.save(user);
        Optional<User> afterUser = userRepository.findByUsername(user.getUsername());
        return afterUser;
    }

    @Transactional
    public Optional<User> category2Modify(String username, Long category2) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.category2Modify(category2);
        userRepository.save(user);
        Optional<User> afterUser = userRepository.findByUsername(user.getUsername());
        return afterUser;
    }

    @Transactional
    public Optional<User> category3Modify(String username, Long category3) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.category3Modify(category3);
        userRepository.save(user);
        Optional<User> afterUser = userRepository.findByUsername(user.getUsername());
        return afterUser;
    }

    @Transactional
    public Optional<User> category4Modify(String username, Long category4) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.category1Modify(category4);
        userRepository.save(user);
        Optional<User> afterUser = userRepository.findByUsername(user.getUsername());
        return afterUser;
    }

    @Transactional
    public Optional<User> category5Modify(String username, Long category5) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.category1Modify(category5);
        userRepository.save(user);
        Optional<User> afterUser = userRepository.findByUsername(user.getUsername());
        return afterUser;
    }


    @Transactional
    public Optional<User> findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);

        return user;
    }


    @Transactional
    public Optional<User> findById(Long id) {

         Optional<User> user = userRepository.findById(id);

        return user;
    }

    //

    @Transactional
    public boolean isPasswordMatching(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

     /*
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // User 객체를 UserDetails 객체로 변환하여 반환
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList()) // 사용자의 권한 정보를 설정하거나 필요에 따라 추가할 수 있습니다.
                .build();
    }

      */






}
