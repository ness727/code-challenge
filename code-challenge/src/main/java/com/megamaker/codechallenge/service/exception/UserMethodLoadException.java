package com.megamaker.codechallenge.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "유저 코드 런타임 에러")
public class UserMethodLoadException extends RuntimeException {
    public UserMethodLoadException() {
        super();
    }
}
