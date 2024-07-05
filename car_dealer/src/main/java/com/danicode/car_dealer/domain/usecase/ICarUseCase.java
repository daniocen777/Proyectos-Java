package com.danicode.car_dealer.domain.usecase;

import com.danicode.car_dealer.domain.dto.Car;

import java.util.List;
import java.util.Optional;

public interface ICarUseCase {

    List<Car> getAll();

    List<Car> getByBrand(Long brandId);

    List<Car> getCarsByPriceLessThan(Double price);

    Optional<Car> getCar(Long id);

    Car save(Car car);

    boolean delete(Long id);
}
