package com.cookies.yam.application.dto;

import com.cookies.yam.domain.Address;
import com.cookies.yam.domain.Category;
import com.cookies.yam.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */

public class UserDto {

    /** 회원 Service 요청(Request) DTO 클래스 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;

        private String username;

        private String password;

        private String nickname;

        private Long address_id;

        private Long category1_id;

        private Long category2_id;

        private Long category3_id;

        private Long category4_id;

        private Long category5_id;

        /* DTO -> Entity */
        public User toEntity() {
            User user = User.builder()
                    .id(id)
                    .username(username)
                    .password(password)
                    .nickname(nickname)
                    .address_ref(address_id)
                    .category1_ref(category1_id)
                    .category2(category2)
                    .category3(category3)
                    .category4(category4)
                    .category5(category5)
                    .build();
            return user;
        }
    }

    /**
     * 인증된 사용자 정보를 세션에 저장하기 위한 클래스
     * 세션을 저장하기 위해 User 엔티티 클래스를 직접 사용하게 되면 직렬화를 해야 하는데,
     * 엔티티 클래스에 직렬화를 넣어주면 추후에 다른 엔티티와 연관관계를 맺을시
     * 직렬화 대상에 다른 엔티티까지 포함될 수 있어 성능 이슈 우려가 있기 때문에
     * 세션 저장용 Dto 클래스 생성
     * */
    @Getter
    public static class Response implements Serializable {

        private final Long id;

        private final String username;

        private final String nickname;

        //private final String email;

        private final String modifiedDate;

        private final Long address_ref;

        private final Category category1;

        private final Category category2;

        private final Category category3;

        private final Category category4;

        private final Category category5;


        /* Entity -> dto */
        public Response(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.nickname = user.getNickname();
            this.address = user.getAddress();
            this.category1 = user.getCategory1();
            this.category2 = user.getCategory2();
            this.category3 = user.getCategory3();
            this.category4 = user.getCategory4();
            this.category5 = user.getCategory5();
            this.modifiedDate = user.getModifiedDate();
        }
    }
}
