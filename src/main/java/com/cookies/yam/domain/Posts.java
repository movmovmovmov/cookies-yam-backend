package com.cookies.yam.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
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

    @Column(name = "limit_user")
    private int limit;

    @Column(name = "total_count")
    private int count;

    @ManyToOne
    @JoinColumn(name="user_ref")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_ref")
    private Address address;

    /*
    // Category FK 생성 오류
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_ref")
    private Category category;

    */

    @Column(name = "category_ref")
    private Long category;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Participate> participate = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Files> files = new ArrayList<>();

    /*
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comment = new ArrayList<>();
    */


}