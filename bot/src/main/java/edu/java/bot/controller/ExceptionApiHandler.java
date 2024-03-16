package edu.java.bot.controller;

import edu.java.bot.dto.response.ApiErrorResponse;
import edu.java.bot.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleException(BadRequestException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
            "Bad Request",
            "400",
            ex.getClass().getSimpleName(),
            ex.getMessage(),
            null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
