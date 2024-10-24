package com.megamaker.codechallenge.exception;

public class UserClassLoadException extends RuntimeException {
    public UserClassLoadException() {
        super();
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
