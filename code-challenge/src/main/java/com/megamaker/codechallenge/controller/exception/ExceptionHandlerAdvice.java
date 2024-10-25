package com.megamaker.codechallenge.controller.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "com.megamaker.codechallenge.controller")
public class ExceptionHandlerAdvice {
    private final MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserRequestLangException.class)
    public ErrorResult userRequestLangException() {
        String code = messageSource.getMessage("error.code.user_request_lang", null, null);
        String message = messageSource.getMessage("error.message.user_request_lang", null, null);
        return new ErrorResult(code, message);
    }

    // 그 외의 모든 예외 처리
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult etcException() {
        String code = messageSource.getMessage("error.code.user_request_lang", null, null);
        String message = messageSource.getMessage("error.message.user_request_lang", null, null);
        return new ErrorResult(code, message);
    }
}
