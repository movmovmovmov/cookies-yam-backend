package com.cookies.yam.domain;

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
    @Column(nullable = false, length = 30, unique = true)
    private String address_depth1;

    // 구, 군
    @Column(nullable = false, length = 30, unique = true)
    private String address_depth2;

    // 동, 면
    @Column(nullable = false, length = 30, unique = true)
    private String address_depth3;


    @OneToMany(mappedBy = "address")
    private List<User> user = new ArrayList<>();


}
