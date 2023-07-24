package com.cookies.yam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // 사용자 인증 처리 설정 (예: 메모리 기반 사용자 인증)
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/vi/user/category1/modify").permitAll() // 시큐리티 적용하지 않을 요청 경로 설정
                .antMatchers("/api/vi/user/category2/modify").permitAll() // 시큐리티 적용하지 않을 요청 경로 설정
                .antMatchers("/api/vi/user/address/modify").permitAll() // 시큐리티 적용하지 않을 요청 경로 설정
                .antMatchers("/api/vi/user/nickname/modify").permitAll() // 시큐리티 적용하지 않을 요청 경로 설정
                .antMatchers("/api/vi/user/detail").permitAll() // 시큐리티 적용하지 않을 요청 경로 설정
                .antMatchers("/api/vi/address/all/list").permitAll() // 시큐리티 적용하지 않을 요청 경로 설정
                .anyRequest().authenticated() // 그 외의 요청은 인증 필요
                .and()
                .formLogin()
                .and()
                .logout()
                .and()
                .csrf().disable(); // CSRF 보안 기능 비활성화 (테스트 시에만 사용)
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}