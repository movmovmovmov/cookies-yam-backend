package com.cookies.yam.presentation;

import com.cookies.yam.application.UserService;
import com.cookies.yam.application.dto.UserDto;

import com.cookies.yam.domain.User;

import com.cookies.yam.interceptor.RestHandlerInterceptor;
import com.cookies.yam.util.JwtTokenUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

import static com.cookies.yam.util.JwtTokenUtil.generateToken;

/**
 * 회원 관련 Controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vi")
public class UserApiController {
    //private final PasswordEncoder passwordEncoder;
    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(RestHandlerInterceptor.class);

    private final UserService userService;

    @GetMapping("/test")
    public String ConnectTest() {
        return "test입니다.";
    }

    /* 회원가입 */
    @PostMapping("/auth/joinProc")
    public void joinProc(@RequestBody UserDto.Request dto) {
        logger.info("CHECK joinProc controller :  ");
        logger.info("CHECK dto.getUsername(): " + dto.getUsername());

        userService.join(dto);
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

    // 로그인 (JWT)

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody UserDto.Request dto) {
        User user = userService.login(dto.getUsername(), dto.getPassword());
        if (user != null) {
            // JWT 토큰 생성 및 반환 로직 구현 (예: 토큰 생성 유틸 클래스 활용)
            String jwtToken = JwtTokenUtil.generateToken(user.getUsername());
            return ResponseEntity.ok(jwtToken);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
        }
    }

    // 로그아웃 Security에서 로그아웃은 기본적으로 POST지만, GET으로 우회
    /*
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "redirect:/";
    }
    */

    /* 회원정보(비밀번호) 수정 */
    @PostMapping("/user/modify")
    public void modify(@RequestBody User request) {
        String username = request.getUsername();
        String password = request.getPassword();
        userService.modify(username, password);
    }

    /* 회원 선호 카테고리 수정 */
    @Async
    @PostMapping("/user/category1/modify")
    public void category1Modify(@RequestBody User request){

        String username = request.getUsername();

        Long category1_id = request.getCategory1_id();
        userService.category1Modify(username, category1_id);
    }
    @Async
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
private String generateJwtToken(User user) {
    // JWT 토큰 생성 로직을 구현합니다.
    // 필요한 정보를 토대로 JWT 토큰을 생성하고 반환합니다.

    // 예시: HS256 알고리즘과 시크릿 키를 사용하여 토큰을 생성합니다.
    String secretKey = "mySecretKey";
    Date expirationDate = new Date(System.currentTimeMillis() + 86400000); // 토큰 만료 시간 설정 (예시: 24시간)

    String token = Jwts.builder()
            .setSubject(user.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(expirationDate)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact();

    return token;
}

}