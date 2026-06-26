package com.test.app.exception;

import com.test.core.exception.custom.ExistingProductException;
import com.test.core.exception.custom.ItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(
                    error.getField(),
                        error.getDefaultMessage()

        ));
        return new ErrorResponse(
                LocalDateTime.now(),
                400,
                errors

        );
    }
    @ExceptionHandler(ExistingProductException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDuplicateProductException(ExistingProductException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return new ErrorResponse(
                LocalDateTime.now(),
                409,
                errors
        );
    }
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleItemNotFoundException(ItemNotFoundException ex){
        Map<String, String> errors = new HashMap<>();
        errors.put("message", ex.getMessage());
        return new ErrorResponse(
                LocalDateTime.now(),
                404,
                errors
        );
    }

}
