package com.cookies.yam.application.dto;

import com.cookies.yam.domain.Applicant;
import com.cookies.yam.domain.Posts;
import com.cookies.yam.domain.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
public class ApplicantDto {


    /** 채팅 참여 (Request) 클래스 비지니스 확장성 고려 따로 구성 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;

        private Long user_id;

        private Long post_id;

        private String status;



        /* Dto -> Entity */
        public Applicant toEntity() {
            Applicant applicant = Applicant.builder()
                    .id(id)
                    .user_id(user_id)
                    .post_id(post_id)
                    .status(status)
                    .build();

            return applicant;
        }
    }

    /**
     * 참여 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */

    @Getter
    public static class Response {
        private final Long id;
        private final Long user_id;
        private final Long post_id;
        private final String status;

        /* Entity -> Dto*/
        public Response(Applicant applicant) {
            this.id = applicant.getId();
            this.user_id = applicant.getUser_id();
            this.post_id = applicant.getPost_id();
            this.status = applicant.getStatus();

        }
    }
}
