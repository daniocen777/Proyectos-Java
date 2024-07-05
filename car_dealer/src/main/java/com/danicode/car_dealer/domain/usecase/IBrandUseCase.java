package com.danicode.car_dealer.domain.usecase;

import com.danicode.car_dealer.domain.dto.Brand;

import java.util.List;
import java.util.Optional;

public interface IBrandUseCase {
    List<Brand> getAll();

    Optional<Brand> getBrand(Long id);

    Brand save(Brand brand);

    Optional<Brand> update(Brand brand);

    boolean delete(Long id);
}
