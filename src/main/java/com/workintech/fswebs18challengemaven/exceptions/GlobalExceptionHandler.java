package com.workintech.fswebs18challengemaven.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardException.class)
    public ResponseEntity<CardErrorResponse> handleCardException(CardException exception) {
        log.error("CardException occurred: {}", exception.getMessage());
        CardErrorResponse errorResponse = new CardErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                exception.getHttpStatus().value()
        );
        return new ResponseEntity<>(errorResponse, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CardErrorResponse> handleGlobalException(Exception exception) {
        log.error("Unexpected error occurred: {}", exception.getMessage());
        CardErrorResponse errorResponse = new CardErrorResponse(
                exception.getMessage(),
                System.currentTimeMillis(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}