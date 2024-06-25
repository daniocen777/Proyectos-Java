package com.danicode.car_dealer.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Map;

// Validar las excepciones en el servicio
@RestControllerAdvice
public class ControllerException {

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<Map<String, String>> emailException(EmailException emailException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap("error", emailException.getMessage()));
    }
}
