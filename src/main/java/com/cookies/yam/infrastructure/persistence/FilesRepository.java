package com.cookies.yam.infrastructure.persistence;

import com.cookies.yam.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilesRepository extends JpaRepository<Files, Long> {

}