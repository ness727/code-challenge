package com.megamaker.codechallenge.presentation.exception;

import com.megamaker.codechallenge.application.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@RequiredArgsConstructor
@RestControllerAdvice(basePackages = "com.megamaker.codechallenge.presentation")
public class ExceptionAdvice {
    private final MessageSource ms;

    // Controller 예외 처리
    @ExceptionHandler
    public ErrorResult userRequestLang(UserRequestLangException e) {
        return createErrorResult("user_request_lang", e);
    }

    // Service 예외 처리
    @ExceptionHandler
    public ErrorResult userClassFormat(UserClassFormatException e) {
        return createErrorResult("user_class_format", e);
    }

    @ExceptionHandler
    public ErrorResult userClassLoad(UserClassLoadException e) {
        return createErrorResult("user_class_load", e);
    }

    @ExceptionHandler
    public ErrorResult userMethodLoad(UserMethodLoadException e) {
        return createErrorResult("user_method_load", e);
    }

    @ExceptionHandler
    public ErrorResult userCodeRuntime(UserCodeRuntimeException e) {
        return createErrorResult("user_code_runtime", e);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class, InvalidDataAccessApiUsageException.class})
    public ErrorResult emptyDataAccess(DataAccessException e) {
        return createErrorResult("empty_data_access", e);
    }

    @ExceptionHandler
    public ErrorResult userNotFoundException(UserNotFoundException e) {
        return createErrorResult("user_not_found", e);
    }

    // 그 외의 모든 예외 처리
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult etcException(Exception e) {
        return createErrorResult("etc", e);
    }

    private ErrorResult createErrorResult(String exName, Exception e) {
        String code = ms.getMessage("code." + exName, null, null);
        String message = ms.getMessage("message." + exName, null, null);
        log.error(message, e);
        return new ErrorResult(code, message);
    }
}
