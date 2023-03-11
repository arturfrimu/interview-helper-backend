package com.arturfrimu.studies.exception;

import com.arturfrimu.studies.exception.ExceptionContainer.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        var exceptionResponse = new ExceptionResponse(now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleAllExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        var exceptionResponse = new ExceptionResponse(now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }
}
