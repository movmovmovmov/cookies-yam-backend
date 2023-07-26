package com.cookies.yam.application;
import com.cookies.yam.domain.Address;
import com.cookies.yam.domain.Category;
import com.cookies.yam.domain.User;
import com.cookies.yam.infrastructure.persistence.AddressRepository;
import com.cookies.yam.infrastructure.persistence.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    //private final BCryptPasswordEncoder encoder;

    @Transactional
    public List<Address> findById(int id) {

         List<Address> address = addressRepository.findById(id);
        return address;
    }

    @Transactional
    public Page<Address> searchAddressByDepth3WithPaging(String depth3, int page, int size) {

        Pageable addressPage = PageRequest.of(page,size);

        return addressRepository.findByDepth3Containing(depth3, addressPage);
    }


    @Transactional
    public List<Address> findAllList() {

        List<Address> address = addressRepository.findAll();

        return address;
    }

}
