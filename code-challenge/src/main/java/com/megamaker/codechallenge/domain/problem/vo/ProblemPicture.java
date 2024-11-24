package com.megamaker.codechallenge.domain.problem.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class ProblemPicture {
    @Column(name = "problem_picture_idx", updatable = false, insertable = false)
    private Integer idx;

    private String name;

    private String url;
}