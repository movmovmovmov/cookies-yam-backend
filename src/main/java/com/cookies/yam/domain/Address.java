package com.cookies.yam.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Address extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 시, 도
    @Column(nullable = false, length = 50, unique = true)
    private String depth1;

    // 구, 군
    @Column(nullable = false, length = 50, unique = true)
    private String depth2;

    // 동, 면

    @Column(nullable = false, length = 50, unique = true)
    private String depth3;



}
