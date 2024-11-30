package com.megamaker.admin.domain.problem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestTestcase {
    private Integer idx;
    private String paramData;
    private String result;
}
