package com.cookies.yam.presentation;

import com.cookies.yam.application.AddressService;
import com.cookies.yam.application.UserService;
import com.cookies.yam.application.dto.AddressDto;
import com.cookies.yam.application.dto.UserDto;
import com.cookies.yam.domain.Address;
import com.cookies.yam.domain.User;
import com.cookies.yam.interceptor.RestHandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * 주소 관련 Controller
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/vi")
public class AddressApiController {
    private static final Logger logger = LoggerFactory.getLogger(RestHandlerInterceptor.class);

    private final AddressService addressService;


    /* 동, 면 검색시 뿌리는 리스트 */
    @PostMapping("/address/list")
    public List<Address> list(AddressDto.Request dto) {
        List<Address> addressList = addressService.findByAddress_depth3(dto.getAddress_depth3());

        return addressList;
    }
}