package com.cookies.yam.presentation;

import com.cookies.yam.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API Controller
 */
@RequiredArgsConstructor
@RequestMapping("/api/vi")
@RestController
public class TestUserApiTestController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;
/*
    @PutMapping("/user")
    public ResponseEntity<String> modify(@RequestBody UserDto.Request dto) {
        userService.modify(dto);

        /* 변경된 세션 등록 */
    /*
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    */
}
