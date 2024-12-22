package com.megamaker.codechallenge.problem.infra;

import com.megamaker.codechallenge.problem.domain.ProblemPicture;
import jakarta.persistence.Embeddable;
import lombok.*;

@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class ProblemPictureVO {
    private String name;

    private String url;


    public ProblemPicture toModel() {
        return new ProblemPicture(name, url);
    }

    public static ProblemPictureVO from(ProblemPicture problemPicture) {
        return new ProblemPictureVO(problemPicture.getName(), problemPicture.getUrl());
    }
}