package com.cookies.yam.infrastructure.persistence;

import com.cookies.yam.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    Optional<File> findByOriginalFileName(String originalFileName);
}