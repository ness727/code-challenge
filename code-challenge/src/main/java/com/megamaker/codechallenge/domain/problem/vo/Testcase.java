package com.megamaker.codechallenge.domain.problem.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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