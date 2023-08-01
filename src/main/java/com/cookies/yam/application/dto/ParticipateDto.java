package com.cookies.yam.application.dto;

import com.cookies.yam.application.PostsService;
import com.cookies.yam.domain.Participate;
import com.cookies.yam.domain.Posts;
import com.cookies.yam.domain.User;
import lombok.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
public class ParticipateDto {

    /** 게시글의 등록과 수정을 처리할 요청(Request) 클래스 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    public static class Request {

        private Long id;

        private Posts post;

        private User user;

        private String status;


        /* Dto -> Entity */
        public Participate toEntity() {
            Participate participate = Participate.builder()
                    .status(status)
                    .build();
                    if(user != null){
                        participate.setUser(user);
                    }

                    if(post != null){
                        participate.setPost(post);
                    }



            return participate;
        }
    }

    /**
     * 게시글 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */

    @Getter
    public static class Response {
        private final Long id;
        private final Posts post;
        private final User user;
        private final String status;

        /* Entity -> Dto*/
        public Response(Participate participate) {
            this.id = participate.getId();
            this.post = participate.getPost();
            this.user = participate.getUser();
            this.status = participate.getStatus();

        }
    }
}
