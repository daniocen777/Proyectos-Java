package com.danicode.car_dealer.domain.repository;

import com.danicode.car_dealer.domain.pojo.Brand;

import java.util.List;
import java.util.Optional;

public interface IBrandRepository {

    List<Brand> getAll();

    Optional<Brand> getBrand(Long id);

    Brand save(Brand brand);

    void delete(Long id);
}
