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

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(length = 100)
    private String password;

    //@Column(nullable = false, length = 50, unique = true)
    //private String email;

    @Column(nullable = false, length = 30, unique = false)
    private String address1;

    @Column(nullable = false, length = 30, unique = false)
    private String address2;

    @Column(nullable = false, length = 30, unique = false)
    private String address3;

    @Column
    private String category1;

    @Column
    private String category2;

    @Column
    private String category3;

    @Column
    private String category4;

    @Column
    private String category5;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /* 회원정보 수정 */
    public void modify( String password) {
        //this.nickname = nickname;
        this.password = password;
    }

    /* 소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트해줘서
     * 기존 데이터를 보존하도록 예외처리 */
    public User updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }


    public String getRoleValue() {
        return this.role.getValue();
    }
}
