package com.cookies.yam.application.dto;

import com.cookies.yam.domain.Posts;
import com.cookies.yam.domain.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * request, response DTO 클래스를 하나로 묶어 InnerStaticClass로 한 번에 관리
 */
public class PostsDto {


    /** 게시글의 등록과 수정을 처리할 요청(Request) 클래스 */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        private Long id;

        private String title;

        private String content;

        private int limit;

        private int count;

        private String createdDate, modifiedDate;

        private int view;

        private Long user_id;

        private Long category_id;

        private Long address_id;

        private String searchValue;

        private String keyword;

        private String order;

        /* Dto -> Entity */
        public Posts toEntity() {
            Posts posts = Posts.builder()
                    .id(id)
                    .title(title)
                    .limit(limit)
                    .count(count)
                    .content(content)
                    .view(0)
                    .user_id(user_id)
                    .category_id(category_id)
                    .address_id(address_id)
                    .build();

            return posts;
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
        private final String title;
        private final String content;
        private final String createdDate, modifiedDate;
        private final int view;

        private final Long category_id;

        private final Long address_id;
        private final Long user_id;
        private final int limit;
        private final int count;

        /* Entity -> Dto*/
        public Response(Posts posts) {
            this.id = posts.getId();
            this.title = posts.getTitle();
            this.content = posts.getContent();
            this.createdDate = posts.getCreatedDate();
            this.modifiedDate = posts.getModifiedDate();
            this.view = posts.getView();
            this.count = posts.getCount();
            this.limit = posts.getLimit();
            this.address_id = posts.getAddress_id();
            this.category_id = posts.getCategory_id();
            this.user_id = posts.getUser_id();
            //this.comments = posts.getComments().stream().map(CommentDto.Response::new).collect(Collectors.toList());
        }
    }
}
