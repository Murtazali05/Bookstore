package ru.shop.core.exception;

public class OldPasswordIncorrectException extends RuntimeException {

    public OldPasswordIncorrectException(String message) {
        super(message);
    }

    public OldPasswordIncorrectException(String message, Throwable cause) {
        super(message, cause);
    }
}
