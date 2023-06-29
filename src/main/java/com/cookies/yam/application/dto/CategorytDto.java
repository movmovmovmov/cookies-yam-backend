package com.cookies.yam.application.dto;

import com.cookies.yam.domain.Category;
import lombok.*;


/**
 * request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
public class CategorytDto {


    /** 카테고리 요청(Request) 클래스 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;

        private String categoryname;


        /* Dto -> Entity */
        public Category toEntity() {
            Category category = Category.builder()
                    .id(id)
                    .categoryname(categoryname)
                    .build();

            return category;
        }
    }

    /**
     * 카테고리 정보를 리턴할 응답(Response) 클래스
     * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
     * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지
     */

    @Getter
    public static class Response {
        private final Long id;
        private final String categoryname;
        //private final List<CommentDto.Response> comments;

        /* Entity -> Dto*/
        public Response(Category category) {
            this.id = category.getId();
            this.categoryname = category.getCategoryname();
            //this.comments = posts.getComments().stream().map(CommentDto.Response::new).collect(Collectors.toList());
        }
    }
}
