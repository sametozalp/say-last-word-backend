package com.ozalp.last_word_service.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<?> handle(EntityNotFoundException e) {
        return ResponseEntity.badRequest().body(Map.of("message", e.getLocalizedMessage()));
    }

    // 1. Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex, WebRequest request) {
        List<String> validationErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        String message = "Invalid field";

        if (!validationErrors.isEmpty()) {
            message = validationErrors.get(0);
        }

        return new ResponseEntity<>(Map.of("message", message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        System.out.println("Error: " + ex.getMessage());
        return new ResponseEntity<>(Map.of("message", "An unknown error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
