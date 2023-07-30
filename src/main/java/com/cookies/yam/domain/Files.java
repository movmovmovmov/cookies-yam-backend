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


    @ManyToOne
    @JoinColumn(name="posts_ref")
    private Posts post;



    public Files(String originalFileName, String renamedFileName, Posts post) {
        this.originalFileName = originalFileName;
        this.renamedFileName = renamedFileName;
        this.post = post;
    }



}



