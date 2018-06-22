package ru.shop.controller;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.shop.service.dto.error.ErrorValidateDTO;
import ru.shop.service.dto.error.ErrorDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestControllerAdvice
public class ExceptionHandlers {
    private static Logger logger = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleThrowable(Throwable ex){
        String errId = UUID.randomUUID().toString();
        logger.error("Server Error, id = {}", errId);

        return new ErrorDTO(ex.getClass().getSimpleName(), ex.getMessage() + ", error id = " + errId);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFoundException(NotFoundException ex){
        logger.warn("404 error, name = {}, msg = {}", ex.getClass().getSimpleName(), ex.getMessage());

        return new ErrorDTO(ex.getClass().getSimpleName(), ex.getMessage());
    }

    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorValidateDTO> handleBindException(BindException ex){
        return this.handleBindingResult(ex.getClass().getSimpleName(), ex.getBindingResult());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorValidateDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        return handleBindingResult(ex.getClass().getSimpleName(), ex.getBindingResult());
    }

    private List<ErrorValidateDTO> handleBindingResult(String name, BindingResult bindingResult){
        List<ErrorValidateDTO> errorDTOList = new ArrayList<>();
        for (int i = 0; i < bindingResult.getAllErrors().size(); i++){
            errorDTOList.add(new ErrorValidateDTO(name, bindingResult.getAllErrors().get(i).getDefaultMessage(), bindingResult.getFieldErrors().get(i).getField(), bindingResult.getAllErrors().get(i).getCode()));
        }
        return errorDTOList;
    }

}
