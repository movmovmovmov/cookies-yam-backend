package com.cookies.yam.infrastructure.persistence;

import com.cookies.yam.domain.Address;
import com.cookies.yam.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    /* 동, 면으로 찾기 */
    List<Address> findByAddress_depth3(String address_depth3);

    List<Address> findById(int id);




}
