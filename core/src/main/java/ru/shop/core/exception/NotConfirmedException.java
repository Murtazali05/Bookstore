package ru.shop.core.exception;

public class NotConfirmedException extends RuntimeException {

    public NotConfirmedException(String message) {
        super(message);
    }

    public NotConfirmedException(String message, Throwable cause) {
        super(message, cause);
    }

}
