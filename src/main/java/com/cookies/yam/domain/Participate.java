package com.cookies.yam.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Participate extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 역방향에서만 참조
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name="post_id")
    private Posts post;

    // 역방향에서만 참조
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column
    private String status;




}