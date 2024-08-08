package com.danicode.movie_rental.core.exception;

import com.danicode.movie_rental.core.dto.ErrorDTO;
import com.danicode.movie_rental.core.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ProducerFoundException.class)
    public ResponseEntity<ApiResponse<String>> producerFoundException(ProducerFoundException exception) {
        ApiResponse<String> response = new ApiResponse<>();
        response.setStatus("Error");
        response.setResults("Error al insertar productor");
        response.setErrors(List.of(new ErrorDTO("Producer", exception.getMessage())));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
