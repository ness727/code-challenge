package com.megamaker.codechallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserAnswer {
    private Long userId;
    private Long problemId;
    private String sourceCode;
}
