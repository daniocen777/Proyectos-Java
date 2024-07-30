package com.danicode.movie_rental.web.controller;

import com.danicode.movie_rental.core.dto.ErrorDTO;
import com.danicode.movie_rental.core.response.ApiResponse;
import com.danicode.movie_rental.domain.dto.pojo.Producer;
import com.danicode.movie_rental.domain.dto.request.ProducerInsertRequest;
import com.danicode.movie_rental.domain.dto.response.ProducerResponse;
import com.danicode.movie_rental.domain.service.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/producers")
public class ProducerController {

    private final ProducerService service;

    public ProducerController(ProducerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ProducerResponse> getAll() {
        List<Producer> producers = this.service.getAll();
        ProducerResponse producerResponse = new ProducerResponse();
        producerResponse.setProducers(producers);

        return new ResponseEntity<>(producerResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Producer>> saveProducer(@Valid @RequestBody ProducerInsertRequest producerInsertRequest,
                                                              BindingResult result) {
        ApiResponse apiResponse = new ApiResponse();
        Producer producer = new Producer();

        if (result.hasErrors()) {
            List<ErrorDTO> errors = new ArrayList<>();
            result.getFieldErrors()
                    .forEach(fieldError -> errors.add(new ErrorDTO(fieldError.getField(), fieldError.getDefaultMessage())));

            apiResponse.setStatus("ERROR");
            apiResponse.setErrors(errors);

            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        producer.setCountry(producerInsertRequest.getCountry());
        producer.setCompanyName(producerInsertRequest.getCompanyName());

        Producer producerInsertResult = this.service.save(producer);

        apiResponse.setStatus("OK");
        apiResponse.setResults(producerInsertResult);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
