package com.megamaker.codechallenge.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "선택 언어를 다시 확인해주세요")
public class UserRequestLangException extends RuntimeException {
    public UserRequestLangException() {
        super();
    }
}
