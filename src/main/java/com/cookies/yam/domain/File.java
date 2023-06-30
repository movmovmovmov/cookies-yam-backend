package com.cookies.yam.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@Getter
@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originalFileName;
    @Column
    private String renamedFileName;

    @Column(name = "post_ref")
    private Long post_id;



    public File(String originalFileName, String renamedFileName) {
        this.originalFileName = originalFileName;
        this.renamedFileName = renamedFileName;
    }

    // getters and setters
}



