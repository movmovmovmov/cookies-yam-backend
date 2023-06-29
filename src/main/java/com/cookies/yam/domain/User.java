package com.cookies.yam.domain;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String username; // 아이디

    @Column
    private String nickname;

    @Column(length = 100)
    private String password;

    @OneToOne
    @JoinColumn(name = "category1_ref")
    private Category category1;

    @OneToOne
    @JoinColumn(name = "category2_ref")
    private Category category2;

    @OneToOne
    @JoinColumn(name = "category3_ref")
    private Category category3;

    @OneToOne
    @JoinColumn(name = "category4_ref")
    private Category category4;

    @OneToOne
    @JoinColumn(name = "category5_ref")
    private Category category5;

    @OneToOne
    @JoinColumn(name = "address_ref")
    private Address address;




    /* 회원정보 수정 */
    public void modify(String nickname, String password, Address address, Category category1, Category category2,
                       Category category3, Category category4, Category category5) {
        this.nickname = nickname;
        this.password = password;
        this.address = address;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.category4 = category4;
        this.category5 = category5;

    }

    /* 소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트해줘서
     * 기존 데이터를 보존하도록 예외처리 */
    public User updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }



}
