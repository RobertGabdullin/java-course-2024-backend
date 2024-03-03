package edu.java.controller;

import edu.java.controller.exception.BadRequestException;
import edu.java.controller.exception.NotFoundException;
import edu.java.controller.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiErrorResponse> handleBadRequestException(BadRequestException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
            "Bad Request",
            "400",
            ex.getClass().getSimpleName(),
            ex.getMessage(),
            null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFoundException(NotFoundException ex) {
        ApiErrorResponse errorResponse = new ApiErrorResponse(
            "Not Found",
            "404",
            ex.getClass().getSimpleName(),
            ex.getMessage(),
            null);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}

