package com.danicode.movie_rental.web.controller;

import com.danicode.movie_rental.domain.dto.pojo.Producer;
import com.danicode.movie_rental.domain.dto.response.ProducerResponse;
import com.danicode.movie_rental.domain.service.ProducerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
