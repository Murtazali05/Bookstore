package ru.shop.exception;

public class UserNotConfirmationException extends RuntimeException {

    public UserNotConfirmationException(String message) {
        super(message);
    }

    public UserNotConfirmationException(String message, Throwable cause) {
        super(message, cause);
    }

}
