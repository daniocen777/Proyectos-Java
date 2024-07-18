package com.danicode.movie_rental.domain.dto.response;

import com.danicode.movie_rental.domain.dto.pojo.Producer;

import java.util.List;

public class ProducerResponse {
    List<Producer> producers;

    public ProducerResponse() {
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }
}
