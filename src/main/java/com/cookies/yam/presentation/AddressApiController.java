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
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    /* 동, 면 검색시 뿌리는 페이지 */
    @GetMapping("/address/list")
    public ResponseEntity<Page<Address>> list(@RequestParam(value = "depth3", defaultValue = "") String depth3,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "pageSize", defaultValue = "20") int pageSize) {
        page = page - 1;
        if(pageSize <= 0 ) { pageSize = 20; }
        if(page < 0 ) { page = 0; }

        Page<Address> addressPage = addressService.searchAddressByDepth3WithPaging(depth3, page, pageSize);
        return new ResponseEntity<>(addressPage, HttpStatus.OK);
    }


}