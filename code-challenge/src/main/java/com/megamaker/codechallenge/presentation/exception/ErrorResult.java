package com.megamaker.codechallenge.presentation.exception;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;
}
