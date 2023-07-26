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
import java.util.HashMap;
import java.util.Map;
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

    /* 회원가입 */
    @PostMapping("/auth/joinProc")
    public User joinProc(@RequestBody UserDto.Request dto) {
        User user = userService.join(dto);
        return user;
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
    public ResponseEntity<?> login(@RequestBody UserDto.Request dto) {
        Map result = userService.login(dto.getUsername(), dto.getPassword());
        Map<String, Object> response = new HashMap<>();


            if (("Login Success").equals(result.get("status"))){
                User user = (User) result.get("user");
                // JWT 토큰 생성 및 반환 로직 구현 (예: 토큰 생성 유틸 클래스 활용)
                String jwtToken = JwtTokenUtil.generateToken(user.getUsername());
                response.put("user", user);
                response.put("jwtToken", jwtToken);

                return ResponseEntity.ok(response);
            } else if (("Not Matches").equals(result.get("status"))) {

                User user = (User) result.get("user");
                response.put("user", user);
                response.put("status", result.get("status") + " Password");
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No User or Invalid credentials!");
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
    @PostMapping("/auth/modify")
    public void modify(@RequestBody User request) {
        String username = request.getUsername();
        String password = request.getPassword();
        userService.modify(username, password);
    }

    /* 회원 선호 카테고리 수정 */
    @Async
    @PostMapping("/user/category1/modify")
    public User category1Modify(@RequestBody UserDto.Request request) {


        String username = request.getUsername();
         Optional<User> afterUser = userService.category1Modify(username, request.getCategory1_id());

        User response = afterUser.orElseGet(User::new);
        return response;
    }

    @Async
    @PostMapping("/user/category2/modify")
    public User category2Modify(@RequestBody UserDto.Request request) {


        String username = request.getUsername();
        Optional<User> afterUser = userService.category2Modify(username, request.getCategory2_id());

        User response = afterUser.orElseGet(User::new);
        return response;
    }

    @Async
    @PostMapping("/user/category3/modify")
    public User category3Modify(@RequestBody UserDto.Request request) {


        String username = request.getUsername();
        Optional<User> afterUser = userService.category3Modify(username, request.getCategory3_id());

        User response = afterUser.orElseGet(User::new);
        return response;
    }

    @Async
    @PostMapping("/user/category4/modify")
    public User category4Modify(@RequestBody UserDto.Request request) {


        String username = request.getUsername();
        Optional<User> afterUser = userService.category4Modify(username, request.getCategory4_id());

        User response = afterUser.orElseGet(User::new);
        return response;
    }

    @Async
    @PostMapping("/user/category5/modify")
    public User category5Modify(@RequestBody UserDto.Request request) {
        String username = request.getUsername();
        Optional<User> afterUser = userService.category5Modify(username, request.getCategory5_id());

        User response = afterUser.orElseGet(User::new);
        return response;
    }


    /* 회원 동네 수정(입력) */
    @PostMapping("/user/address/modify")
    public User addressModify(@RequestBody UserDto.Request request){
        Long address_id = request.getAddress_id();
        String username = request.getUsername();
        User response = userService.addressModify(username, address_id);
        return response;
    }

    /* 회원 닉네임 수정 */
    @PostMapping("/user/nickname/modify")
    public User nicknameModify(@RequestBody UserDto.Request request){
        String username = request.getUsername();
        String nickname = request.getNickname();
        User response = userService.nicknameModify(username, nickname);
        return response;
    }

    /* 단순 회원정보 조회(username, 아이디) */
    @PostMapping("/user/detail")
    public User info(@RequestBody UserDto.Request request) {
        String username = request.getUsername();
        Optional<User> checkUser = userService.findByUsername(username);

        User user = checkUser.orElseGet(User::new);

        return user;
    }

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