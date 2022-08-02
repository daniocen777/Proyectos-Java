package com.danicode.blog.exception;

import com.danicode.blog.dto.DetalleError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

// ControllerAdvice => Permite manejar excepciones
@ControllerAdvice
public class GlobalExceptionHandler {

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
}
