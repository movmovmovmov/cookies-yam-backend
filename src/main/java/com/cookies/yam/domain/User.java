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

    @Column
    private String password;

    @Column
    private Long category1_ref;

    @Column
    private Long category2_ref;

    @Column
    private Long category3_ref;

    @Column
    private Long category4_ref;

    @Column
    private Long category5_ref;

    @Column
    private Long address_ref;



    /* 회원정보 수정 */
    public void modify(String password) {
        this.password = password;

    }
    /* 회원 동네 정보 입력 */
    public void addressModify(Long address_ref){
        this.address_ref = address_ref;
    }

    /* 닉네임 변경 */
    public void nicknameModify(String nickname){
        this.nickname = nickname;
    }

    /* 회원 선호 카테고리 입력*/
    public void category1Modify(Long category1){
        this.category1_ref = category1_ref;
    }
    public void category2Modify(Long category2){
        this.category2_ref = category2_ref;
    }
    public void category3Modify(Long category3){
        this.category3_ref = category3_ref;
    }
    public void category4(Long category4){
        this.category4_ref = category4_ref;
    }
    public void category5Modify(Long category5){
        this.category5_ref = category5_ref;
    }

    /* 소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트해줘서
     * 기존 데이터를 보존하도록 예외처리 */
    public User updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }



}
