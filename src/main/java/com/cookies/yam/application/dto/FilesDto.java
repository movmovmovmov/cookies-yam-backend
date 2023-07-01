package com.cookies.yam.application.dto;

import com.cookies.yam.domain.Files;
import lombok.*;

/**
 * request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
public class FilesDto {


    /** 첨부파일 (Request) 클래스 비지니스 확장성 고려 따로 구성 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;

        private String originalFileName;

        private String renamedFileName;

        private Long postsId;



        /* Dto -> Entity */
        public Files toEntity() {
            Files files = Files.builder()
                    .id(id)
                    .originalFileName(originalFileName)
                    .renamedFileName(renamedFileName)
                    .postsId(postsId)
                    .build();
            return files;
        }
    }

    /**
     * 파일 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */

    @Getter
    public static class Response {
        private final Long id;
        private final String originalFileName;
        private final String renamedFileName;
        private final Long postsId;



        /* Entity -> Dto*/
        public Response(Files files) {
            this.id = files.getId();
            this.originalFileName = files.getOriginalFileName();
            this.renamedFileName = files.getRenamedFileName();
            this.postsId = files.getPostsId();


        }
    }
}
