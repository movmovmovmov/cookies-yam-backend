package com.cookies.yam.infrastructure.persistence;

import com.cookies.yam.application.dto.UserDto;
import com.cookies.yam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /* Security */
    Optional<User> findByUsername(String username);


    /* 중복 검사> 중복인 경우 true, 중복되지 않은경우 false 리턴 */
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);



}
