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

    @ManyToOne(optional = false)
    @JoinTable(name = "category_user",
            joinColumns = {@JoinColumn(name = "category1_ref", referencedColumnName = "category1_ref")},
            inverseJoinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")})
    private Category category1;

    @ManyToOne(optional = false)
    @JoinTable(name = "category_user",
            joinColumns = {@JoinColumn(name = "category2_ref", referencedColumnName = "category2_ref")},
            inverseJoinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")})
    private Category category2;

    @ManyToOne(optional = false)
    @JoinTable(name = "category_user",
            joinColumns = {@JoinColumn(name = "category3_ref", referencedColumnName = "category3_ref")},
            inverseJoinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")})
    private Category category3;

    @ManyToOne(optional = false)
    @JoinTable(name = "category_user",
            joinColumns = {@JoinColumn(name = "category4_ref", referencedColumnName = "category4_ref")},
            inverseJoinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")})
    private Category category4;

    @ManyToOne(optional = false)
    @JoinTable(name = "category_user",
            joinColumns = {@JoinColumn(name = "category5_ref", referencedColumnName = "category5_ref")},
            inverseJoinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")})
    private Category category5;



    @ManyToOne(optional = false)
    @JoinTable(name = "address_user",
            joinColumns = {@JoinColumn(name = "address_ref", referencedColumnName = "address_ref")},
            inverseJoinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")})
    private Address address;



    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

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


    public String getRoleValue() {
        return this.role.getValue();
    }


}
