package com.megamaker.admin.domain.problem;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class Testcase {
    private Long id;

    @Column(name = "param_data")
    private String paramData;

    private String result;
}