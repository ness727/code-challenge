package com.megamaker.codechallenge.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RequestUserAnswer {
    private Long problemId;
    private String lang;
    private String sourceCode;
}
