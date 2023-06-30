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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;


import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

/**
 * 회원 관련 Controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vi")
public class UserApiController {
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
    public String checkUserName(UserDto.Request dto) {
        logger.info("CHECK /auth/join/check controller :  ");

        boolean result = userService.existsByUsername(dto.getUsername());

        if (result) {
            return "already used";
        } else {
            return "null";
        }
    }

    /* 로그인 (JWT) */
    @PostMapping("/auth/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/user/user-login";
    }

    /* 로그아웃 (JWT) Security에서 로그아웃은 기본적으로 POST지만, GET으로 우회 */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "redirect:/";
    }

    /* 회원정보(비밀번호) 수정 */
    @PostMapping("/user/modify")
    public void modify(UserDto.Request dto) {
        userService.modify(dto);
    }

    /* 회원 선호 카테고리 수정 */
    @PostMapping("/user/category/modify")
    public void categoryModify(UserDto.Request dto){

    }

    /* 회원 동네 수정(입력) */
    @PostMapping("/user/address/modify")
    public void addressModify(UserDto.Request dto){
        userService.addressModify(dto);
    }

    /* 회원 닉네임 수정 */
    @PostMapping("/user/nickname/modify")
    public void nicknameModify(UserDto.Request dto){
        userService.nicknameModify(dto);
    }

    /* 단순 회원정보 조회(username, 아이디) */
    @PostMapping("/user/detail")
    public User info(UserDto.Request dto) {
        Optional<User> checkUser = userService.findByUsername(dto.getUsername());

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