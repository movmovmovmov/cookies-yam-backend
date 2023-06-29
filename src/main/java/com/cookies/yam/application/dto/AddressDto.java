package com.cookies.yam.application.dto;

import com.cookies.yam.domain.Address;
import com.cookies.yam.domain.Posts;
import com.cookies.yam.domain.User;
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

        private String address_depth1;

        private String address_depth2;

        private String address_depth3;

        private List<User> users;

        /* Dto -> Entity */
        public Address toEntity() {
            Address address = Address.builder()
                    .id(id)
                    .address_depth1(address_depth1)
                    .address_depth2(address_depth2)
                    .address_depth3(address_depth3)
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
        private final String address_depth1;
        private final String address_depth2;
        private final String address_depth3;



        /* Entity -> Dto*/
        public Response(Address address) {
            this.id = address.getId();
            this.address_depth1 = address.getAddress_depth1();
            this.address_depth2 = address.getAddress_depth2();
            this.address_depth3 = address.getAddress_depth3();


        }
    }
}
