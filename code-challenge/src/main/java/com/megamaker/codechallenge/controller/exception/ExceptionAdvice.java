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
        return createErrorResult("user_request_lang");
    }

    // Service 예외 처리
    @ExceptionHandler(UserClassFormatException.class)
    public ErrorResult userClassFormat() {
        return createErrorResult("user_class_format");
    }

    @ExceptionHandler(UserClassLoadException.class)
    public ErrorResult userClassLoad() {
        return createErrorResult("user_class_load");
    }

    @ExceptionHandler(UserMethodLoadException.class)
    public ErrorResult userMethodLoad() {
        return createErrorResult("user_method_load");
    }

    @ExceptionHandler(UserCodeRuntimeException.class)
    public ErrorResult userCodeRuntime() {
        return createErrorResult("user_code_runtime");
    }

    // 그 외의 모든 예외 처리
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult etcException() {
        return createErrorResult("etc");
    }

    private ErrorResult createErrorResult(String exName) {
        String code = ms.getMessage("code." + exName, null, null);
        String message = ms.getMessage("message." + exName, null, null);
        return new ErrorResult(code, message);
    }
}
