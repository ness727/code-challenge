package com.megamaker.codechallenge.web.exceptionadvice;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ErrorResult {
    private String code;
    private String message;
}
