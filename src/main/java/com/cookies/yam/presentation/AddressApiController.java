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
    public List<Address> list(@RequestBody Address request) {
        String depth3 = request.getDepth3();
        List<Address> address = addressService.findByDepth3(depth3);

        return address;
    }

    @PostMapping("/address/all")
    public List<Address> All() {

        List<Address> address = addressService.findAllList();

        return address;
    }

}