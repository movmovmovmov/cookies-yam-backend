package com.cookies.yam.presentation;

import com.cookies.yam.application.UserService;
import com.cookies.yam.application.dto.UserDto;
import com.cookies.yam.application.validator.CustomValidators;
import com.cookies.yam.domain.User;
import com.cookies.yam.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
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

    private final UserService userService;

    //private final AuthenticationManager authenticationManager;

    //private final CustomValidators.UsernameValidator UsernameValidator;

    /* 커스텀 유효성 검증을 위해 추가 */
    /*@InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(UsernameValidator);
    }

     */
    /* 기존의 회원가입 페이지 사용X */
    @GetMapping("/auth/join")
    public String join() {
        return "/user/user-join";
    }

    /* 회원가입 */
    @PostMapping("/auth/joinProc")
    public void joinProc(UserDto.Request dto) {

        userService.userJoin(dto);
        //return "redirect:/auth/login";
    }

    /* ID 체크 */
    @Async
    @PostMapping("/auth/join/check")
    public String checkUserName(@RequestParam(value = "userName" ) String userName) {
        Optional<User> user = userService.findByUsername(userName);

        if (user.isPresent()) {
            return user.get().getUsername();
        } else {
            return "null";
        }
    }

    @GetMapping("/auth/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false)String exception,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/user/user-login";
    }

    /* Security에서 로그아웃은 기본적으로 POST지만, GET으로 우회 */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        /*
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

         */
        return "redirect:/";
    }

    /* 회원정보 관련 수정 */
    @GetMapping("/modify")
    public void modify(UserDto.Response user, Model model) {
        if (user != null) {
            model.addAttribute("user", user);
        }

    }


    @PutMapping("/user")
    public ResponseEntity<String> modify(@RequestBody UserDto.Request dto) {
        userService.modify(dto);

        /* 변경된 세션 등록 */
        /*Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        */
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}