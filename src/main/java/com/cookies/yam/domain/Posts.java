package com.cookies.yam.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @Column(name = "user_ref")
    private Long user_id;

    @Column(name = "limit_user")
    private int limit;

    @Column(name = "total_count")
    private int count;

    @Column(name = "address_ref")
    private Long address_id;

    @Column(name = "category_ref")
    private Long category_id;



    /*
    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Comment> comments;
    */
    /* 게시글 수정 */

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }


}