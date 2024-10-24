package com.megamaker.codechallenge.service.exception;

public class UserClassLoadException extends RuntimeException {
    public UserClassLoadException() {
        super("유저 코드 컴파일 에러");
    }

    public UserClassLoadException(String message) {
        super(message);
    }

    public UserClassLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserClassLoadException(Throwable cause) {
        super(cause);
    }
}
