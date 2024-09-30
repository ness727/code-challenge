package com.megamaker.codechallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswer {
    private Long userId;
    private Long problemId;
    private String sourceCode;
}
