package com.megamaker.admin.domain.problem.vo;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class ProblemPicture {
    @Column(name = "problem_pictures_idx", updatable = false, insertable = false)
    private Integer idx;

    private String name;

    private String url;
}