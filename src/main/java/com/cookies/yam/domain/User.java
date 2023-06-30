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

    @Column(name = "category1_ref")
    private Long category1_id;

    @Column(name = "category2_ref")
    private Long category2_id;

    @Column(name = "category3_ref")
    private Long category3_id;

    @Column(name = "category4_ref")
    private Long category4_id;

    @Column(name = "category5_ref")
    private Long category5_id;

    @Column(name = "address_ref")
    private Long address_id;

    @Column
    private String status;

    @Column
    private String agree_loca_at;

    /* 회원정보 수정 */
    public void modify(String password) {
        this.password = password;

    }
    /* 회원 동네 정보 입력 */
    public void addressModify(Long address_id){
        this.address_id = address_id;
    }

    /* 닉네임 변경 */
    public void nicknameModify(String nickname){
        this.nickname = nickname;
    }

    /* 회원 선호 카테고리 입력*/
    public void category1Modify(Long category1_id){
        this.category1_id = category1_id;
    }
    public void category2Modify(Long category2_id){
        this.category2_id = category2_id;
    }
    public void category3Modify(Long category3_id){
        this.category3_id = category3_id;
    }
    public void category4Modify(Long category4_id){
        this.category4_id = category4_id;
    }
    public void category5Modify(Long category5_id){
        this.category5_id = category5_id;
    }

    /* 소셜로그인시 이미 등록된 회원이라면 수정날짜만 업데이트해줘서
     * 기존 데이터를 보존하도록 예외처리 */
    public User updateModifiedDate() {
        this.onPreUpdate();
        return this;
    }



}
