package com.arturfrimu.interview.helper.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static java.time.LocalDateTime.now;
import static java.util.stream.Collectors.joining;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ExceptionContainer.ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ExceptionContainer.ResourceNotFoundException ex, WebRequest request) {
        var exceptionResponse = new ExceptionResponse(now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request) {
        var errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(joining(", "));

        var exceptionResponse = new ExceptionResponse(now(), errorMessage, request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    protected ResponseEntity<ExceptionResponse> handleMethodArgumentNotValid(HttpMediaTypeNotSupportedException ex, WebRequest request) {
        var errorMessage = String.format("The media type '%s' is not supported. Supported media types are %s",
                ex.getContentType(), ex.getSupportedMediaTypes());

        var exceptionResponse = new ExceptionResponse(now(), errorMessage, request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<ExceptionResponse> handleThrowable(Throwable ex, WebRequest request) {
        var exceptionResponse = new ExceptionResponse(now(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }
}
