package com.cookies.yam.presentation;

import com.cookies.yam.application.UserService;
import com.cookies.yam.application.dto.UserDto;
import com.cookies.yam.application.validator.CustomValidators;
import com.cookies.yam.domain.User;
import com.cookies.yam.infrastructure.persistence.UserRepository;
import com.cookies.yam.interceptor.RestHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 회원 관련 Controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vi")
public class UserApiController {
    @Autowired
    private AuthenticationManager authenticationManager;
    private static final Logger logger = LoggerFactory.getLogger(RestHandlerInterceptor.class);

    private final UserService userService;

    @GetMapping("/test")
    public String ConnectTest() {
        return "test입니다.";
    }

    /* 회원가입 */
    @PostMapping("/auth/joinProc")
    public void joinProc(UserDto.Request dto) {
        logger.info("CHECK joinProc controller :  ");
        logger.info("CHECK dto.getUsername(): " + dto.getUsername());
        userService.userJoin(dto);
    }

    /* ID 중복 체크 */
    @Async
    @PostMapping("/auth/join/check")
    public boolean checkUserName(@RequestBody User request) {
        String username = request.getUsername();
        logger.info("CHECK /auth/join/check controller :  ");

        boolean result = userService.existsByUsername(username);
        // True = 이미 있는 사용자, False = 없는 사용자
        return result;
    }

    /* 로그인 (JWT) */
    @PostMapping("/auth/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = userService.generateToken(authentication);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("status","success");
        return ResponseEntity.ok(response);
    }

    /* 로그아웃 Security에서 로그아웃은 기본적으로 POST지만, GET으로 우회 */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "redirect:/";
    }

    /* 회원정보(비밀번호) 수정 */
    @PostMapping("/user/modify")
    public void modify(@RequestBody User request) {
        String username = request.getUsername();
        String password = request.getPassword();
        userService.modify(username, password);
    }

    /* 회원 선호 카테고리 수정 */
    @PostMapping("/user/category1/modify")
    public void category1Modify(@RequestBody User request){
        String username = request.getUsername();
        Long category1_id = request.getCategory1_id();
        userService.category1Modify(username, category1_id);

    }
    @PostMapping("/user/category2/modify")
    public void category2Modify(@RequestBody User request){
        String username = request.getUsername();
        Long category2_id = request.getCategory2_id();
        userService.category2Modify(username, category2_id);
    }

    /* 회원 동네 수정(입력) */
    @PostMapping("/user/address/modify")
    public void addressModify(@RequestBody User request){
        Long address_id = request.getAddress_id();
        String username = request.getUsername();
        userService.addressModify(username, address_id);
    }

    /* 회원 닉네임 수정 */
    @PostMapping("/user/nickname/modify")
    public void nicknameModify(@RequestBody User request){
        String username = request.getUsername();
        String nickname = request.getNickname();
        userService.nicknameModify(username, nickname);
    }

    /* 단순 회원정보 조회(username, 아이디) */
    @PostMapping("/user/detail")
    public User info(@RequestBody User request) {
        logger.info("CHECK /user/detail controller :  ");
        logger.info("CHECK User :  " + request);
        String username = request.getUsername();
        Optional<User> checkUser = userService.findByUsername(username);

        User user = checkUser.orElseGet(User::new);

        return user;
    }


/*
    @PutMapping("/user")
    public ResponseEntity<User> modify(@RequestBody UserDto.Request dto) {
        //userService.modify(dto);

        /* 변경된 세션 등록 */
        /*Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        */
       // return new ResponseEntity<>("success", HttpStatus.OK);
    /*}

    */
}