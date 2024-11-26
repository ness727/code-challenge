package com.megamaker.admin.domain.problem.vo;

import jakarta.persistence.*;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Testcase {
    @Column(name = "testcase_idx", updatable = false, insertable = false)
    private Integer idx;

    @Column(name = "param_data")
    private String paramData;

    private String result;

    public Testcase(String paramData, String result) {
        this.paramData = paramData;
        this.result = result;
    }
}