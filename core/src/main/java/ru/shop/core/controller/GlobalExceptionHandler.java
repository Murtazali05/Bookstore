package ru.shop.core.controller;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.shop.core.exception.AlreadyExistsException;
import ru.shop.core.exception.OldPasswordIncorrectException;
import ru.shop.core.service.dto.error.ErrorDTO;
import ru.shop.core.service.dto.error.ErrorMapDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleThrowable(Throwable ex) {
        String errId = UUID.randomUUID().toString();
        logger.error("Server Error, name={}, message={}, id = {}", ex.getClass().getSimpleName(), ex.getMessage(), errId);

        return new ErrorDTO(ex.getClass().getSimpleName(), ex.getMessage() + ", error id = " + errId);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNoHandlerFoundException(NoHandlerFoundException ex) {
        logger.warn("404 error, name = {}, msg = {}", ex.getClass().getSimpleName(), ex.getMessage());

        return new ErrorDTO(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFoundException(NotFoundException ex) {
        logger.warn("404 error, name = {}, msg = {}", ex.getClass().getSimpleName(), ex.getMessage());

        return new ErrorDTO(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMapDTO handleBindException(BindException ex) {
        return handleBindingResult(ex.getClass().getSimpleName(), "handle.BindException.validation.message", ex.getBindingResult());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMapDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        return handleBindingResult(ex.getClass().getSimpleName(), "handle.MethodArgumentNotValidException.validation.message", ex.getBindingResult());
    }

    @ExceptionHandler({BadCredentialsException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleBadCredentialsException(BadCredentialsException ex) {
        logger.warn("401 exception, name = {}, msg = {}", ex.getClass().getSimpleName(), ex.getMessage());

        return new ErrorDTO(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO handleAlreadyExistsException(AlreadyExistsException ex) {
        logger.warn("409 exception, name = {}, msg = {}", ex.getClass().getSimpleName(), ex.getMessage());

        return new ErrorDTO(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleIllegalException(RuntimeException ex) {
        return new ErrorDTO(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler({OldPasswordIncorrectException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleOldPasswordIncorrectException(OldPasswordIncorrectException ex) {
        return new ErrorDTO(ex.getClass().getSimpleName(), ex.getMessage());
    }

    private ErrorMapDTO handleBindingResult(String exception, String message, BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>(bindingResult.getAllErrors().size());

        for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
            errors.put(bindingResult.getFieldErrors().get(i).getField(), bindingResult.getAllErrors().get(i).getDefaultMessage());
        }

        return new ErrorMapDTO(exception, message, errors);
    }
}
