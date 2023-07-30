package com.cookies.yam.infrastructure.persistence;

import com.cookies.yam.domain.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {
    /*
    @Modifying
    @Query("update Posts p set p.view = p.view + 1 where p.id = :id")
    int updateView(Long id);


     */
    Page<Posts> findByTitleContaining(String keyword, Pageable pageable);

    Optional<Posts> findById(Long id);


}
