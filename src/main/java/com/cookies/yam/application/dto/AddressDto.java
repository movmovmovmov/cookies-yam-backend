package com.cookies.yam.application.dto;

import com.cookies.yam.domain.Address;
import com.cookies.yam.domain.Posts;
import com.cookies.yam.domain.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
public class AddressDto {


    /** 채팅 참여 (Request) 클래스 비지니스 확장성 고려 따로 구성 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;

        private String depth1;

        private String depth2;

        private String depth3;



        /* Dto -> Entity */
        public Address toEntity() {
            Address address = Address.builder()
                    .id(id)
                    .depth1(depth1)
                    .depth2(depth2)
                    .depth3(depth3)
                    .build();
            return address;
        }
    }

    /**
     * 주소 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */

    @Getter
    public static class Response {
        private final Long id;
        private final String depth1;
        private final String depth2;
        private final String depth3;



        /* Entity -> Dto*/
        public Response(Address address) {
            this.id = address.getId();
            this.depth1 = address.getDepth1();
            this.depth2 = address.getDepth2();
            this.depth3 = address.getDepth3();


        }
    }
}
