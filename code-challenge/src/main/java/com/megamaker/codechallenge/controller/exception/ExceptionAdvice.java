package com.megamaker.codechallenge.controller.exception;

import com.megamaker.codechallenge.service.exception.UserClassFormatException;
import com.megamaker.codechallenge.service.exception.UserClassLoadException;
import com.megamaker.codechallenge.service.exception.UserCodeRuntimeException;
import com.megamaker.codechallenge.service.exception.UserMethodLoadException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "com.megamaker.codechallenge.controller")
public class ExceptionAdvice {
    private final MessageSource ms;

    // Controller 예외 처리
    @ExceptionHandler(UserRequestLangException.class)
    public ErrorResult userRequestLang() {
        String code = ms.getMessage("code.user_request_lang", null, null);
        String message = ms.getMessage("message.user_request_lang", null, null);
        return new ErrorResult(code, message);
    }

    // Service 예외 처리
    @ExceptionHandler(UserClassFormatException.class)
    public ErrorResult userClassFormat() {
        String code = ms.getMessage("code.user_class_format", null, null);
        String message = ms.getMessage("message.user_class_format", null, null);
        return new ErrorResult(code, message);
    }

    @ExceptionHandler(UserClassLoadException.class)
    public ErrorResult userClassLoad() {
        String code = ms.getMessage("code.user_class_load", null, null);
        String message = ms.getMessage("message.user_class_load", null, null);
        return new ErrorResult(code, message);
    }

    @ExceptionHandler(UserMethodLoadException.class)
    public ErrorResult userMethodLoad() {
        String code = ms.getMessage("code.user_method_load", null, null);
        String message = ms.getMessage("message.user_method_load", null, null);
        return new ErrorResult(code, message);
    }

    @ExceptionHandler(UserCodeRuntimeException.class)
    public ErrorResult userCodeRuntime() {
        String code = ms.getMessage("code.user_code_runtime", null, null);
        String message = ms.getMessage("message.user_code_runtime", null, null);
        return new ErrorResult(code, message);
    }

    // 그 외의 모든 예외 처리
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult etcException() {
        String code = ms.getMessage("code.etc", null, null);
        String message = ms.getMessage("message.etc", null, null);
        return new ErrorResult(code, message);
    }
}
