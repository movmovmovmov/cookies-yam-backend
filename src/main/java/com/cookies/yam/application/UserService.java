package com.cookies.yam.application;

import com.cookies.yam.application.dto.PostsDto;
import com.cookies.yam.domain.Address;
import com.cookies.yam.domain.Category;
import com.cookies.yam.domain.Posts;
import com.cookies.yam.domain.User;
import com.cookies.yam.infrastructure.persistence.UserRepository;
import com.cookies.yam.application.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private static final String SECRET_KEY = "yamyampoten5"; // 비밀키
    private static final long EXPIRATION_TIME = 3600000; //1시간
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    //private final BCryptPasswordEncoder encoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                new ArrayList<>()
        );

    }
    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Map<String, Object> claims = new HashMap<>();
        // 필요한 클레임 정보 추가

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
    public Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    /* 회원가입 */
    @Transactional
    public void userJoin(UserDto.Request dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

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
    public void modify(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        //String encPassword = encoder.encode(dto.getPassword());

        user.modify(password);
    }

    /* 회원 동네 정보 입력 */
    public void addressModify(String username, Long addressId){
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

        user.addressModify(addressId);
        userRepository.save(user);
    }

    /* 회원 닉네임 변경 */
    public void nicknameModify(String username, String nickname) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.nicknameModify(nickname);
        userRepository.save(user);
    }

    @Transactional
    public void category1Modify(String username, Long category1) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.category1Modify(category1);
        userRepository.save(user);

    }
    @Transactional
    public void category2Modify(String username, Long category2) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.category2Modify(category2);
        userRepository.save(user);
    }
    @Transactional
    public void category3Modify(String username, Long category3) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.category3Modify(category3);
        userRepository.save(user);
    }
    @Transactional
    public void category4Modify(String username, Long category4) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.category4Modify(category4);
        userRepository.save(user);
    }
    @Transactional
    public void category5Modify(String username, Long category5) {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        user.category5Modify(category5);
        userRepository.save(user);
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
