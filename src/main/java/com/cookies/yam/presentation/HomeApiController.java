package com.cookies.yam.presentation;

import com.cookies.yam.application.UserService;
import com.cookies.yam.application.dto.UserDto;
import com.cookies.yam.domain.User;
import com.cookies.yam.interceptor.RestHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 회원 관련 Controller
 */
@RequiredArgsConstructor
@RestController
public class HomeApiController {
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(RestHandlerInterceptor.class);

    private final UserService userService;

    @GetMapping("/")
    public String ConnectTest() {
        return "진입여부 test입니다.";
    }

}