package com.cookies.yam.domain;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Files extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;
    @Column
    private String renamedFileName;

    @Column(name = "posts_ref")
    private Long postsId;



    public Files(String originalFileName, String renamedFileName, Long posts_id) {
        this.originalFileName = originalFileName;
        this.renamedFileName = renamedFileName;
        this.postsId = postsId;
    }



}



