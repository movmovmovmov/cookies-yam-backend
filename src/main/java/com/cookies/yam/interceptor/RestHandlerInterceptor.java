package com.cookies.yam.interceptor;

import com.cookies.yam.application.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@Component
/* true -> 컨트롤러로 요청을 전달,  false -> 요청을 중단 */
public class RestHandlerInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RestHandlerInterceptor.class);
    /* REQUEST JSON -> DTO */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        logger.info("preHandle Process URI: " + uri);

        if (false) {
            String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

            logger.info("requestBody value TEST: " + requestBody);
            ObjectMapper objectMapper = new ObjectMapper();
            UserDto userDto = objectMapper.readValue(requestBody, UserDto.class);

            // 변환된 DTO를 요청 속성에 저장합니다.
            request.setAttribute("user", userDto);

            return true;

        } else if(true) {
            return true;
        } else {
            return true;
        }
        /*
        String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));


        //ObjectMapper objectMapper = new ObjectMapper();
        //MyDTO myDTO = objectMapper.readValue(requestBody, MyDTO.class);


        //request.setAttribute("myDTO", myDTO);

         */

    }




}
