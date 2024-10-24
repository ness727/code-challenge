package com.megamaker.codechallenge.exception;

public class UserMethodLoadException extends RuntimeException {
    public UserMethodLoadException() {
        super();
    }

    public UserMethodLoadException(String message) {
        super(message);
    }

    public UserMethodLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserMethodLoadException(Throwable cause) {
        super(cause);
    }
}
