package com.cookies.yam.infrastructure.persistence;

import com.cookies.yam.domain.Address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    /* 동, 면으로 찾기 */
    List<Address> findByDepth3(String depth3);

    List<Address> findById(int id);

    Page<Address> findByDepth3Containing(String depth3, Pageable pageable);



}
