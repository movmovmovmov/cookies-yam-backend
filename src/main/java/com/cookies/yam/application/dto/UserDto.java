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
                    .address_id(address_id)
                    .category1_id(category1_id)
                    .category2_id(category2_id)
                    .category3_id(category3_id)
                    .category4_id(category4_id)
                    .category5_id(category5_id)
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

        private final Long address_id;

        private final Long category1_id;

        private final Long category2_id;

        private final Long category3_id;

        private final Long category4_id;

        private final Long category5_id;


        /* Entity -> dto */
        public Response(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.nickname = user.getNickname();
            this.address_id = user.getAddress_id();
            this.category1_id = user.getCategory1_id();
            this.category2_id = user.getCategory2_id();
            this.category3_id = user.getCategory3_id();
            this.category4_id = user.getCategory4_id();
            this.category5_id = user.getCategory5_id();
            this.modifiedDate = user.getModifiedDate();
        }
    }
}
