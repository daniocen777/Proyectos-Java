package com.danicode.car_dealer.domain.service;

import com.danicode.car_dealer.domain.pojo.Brand;

import java.util.List;
import java.util.Optional;

public interface IBrandService {
    List<Brand> getAll();

    Optional<Brand> getBrand(Long id);

    Brand save(Brand brand);

    Optional<Brand> update(Brand brand);

    boolean delete(Long id);
}
