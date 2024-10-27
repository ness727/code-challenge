package com.megamaker.codechallenge.controller.exception;

import com.megamaker.codechallenge.service.exception.UserClassFormatException;
import com.megamaker.codechallenge.service.exception.UserClassLoadException;
import com.megamaker.codechallenge.service.exception.UserCodeRuntimeException;
import com.megamaker.codechallenge.service.exception.UserMethodLoadException;
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

    @ExceptionHandler
    public ErrorResult userMethodLoad(UserMethodLoadException e) {
        log.error("유저 메서드 조회 오류", e);
        return createErrorResult("user_method_load");
    }

    @ExceptionHandler
    public ErrorResult userCodeRuntime(UserCodeRuntimeException e) {
        log.error("유저 코드 런타임 예외", e);
        return createErrorResult("user_code_runtime");
    }

    @ExceptionHandler({EmptyResultDataAccessException.class, InvalidDataAccessApiUsageException.class})
    public ErrorResult emptyDataAccess(DataAccessException e) {
        log.error("DB 데이터 조회 오류", e);
        return createErrorResult("empty_data_access");
    }

    // 그 외의 모든 예외 처리
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult etcException(Exception e) {
        log.error("기타 예외 발생", e);
        return createErrorResult("etc");
    }

    private ErrorResult createErrorResult(String exName) {
        String code = ms.getMessage("code." + exName, null, null);
        String message = ms.getMessage("message." + exName, null, null);
        return new ErrorResult(code, message);
    }
}
