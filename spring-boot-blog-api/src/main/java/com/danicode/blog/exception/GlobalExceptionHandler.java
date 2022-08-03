package com.danicode.blog.exception;

import com.danicode.blog.dto.DetalleError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// ControllerAdvice => Permite manejar excepciones
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DetalleError> resourceNotFoundExceptionHandler(
            ResourceNotFoundException exception,
            WebRequest webRequest) {
        DetalleError detalleError = new DetalleError(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(detalleError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogException.class)
    public ResponseEntity<DetalleError> blogExceptionHandler(
            BlogException exception,
            WebRequest webRequest) {
        DetalleError detalleError = new DetalleError(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(detalleError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DetalleError> globalExceptionHandler(
            Exception exception,
            WebRequest webRequest) {
        DetalleError detalleError = new DetalleError(new Date(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(detalleError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Para las validaciones

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String key = ((FieldError) error).getField();
            String value = error.getDefaultMessage();
            errors.put(key, value);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
