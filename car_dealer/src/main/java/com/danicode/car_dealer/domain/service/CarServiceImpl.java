package com.danicode.car_dealer.domain.service;

import com.danicode.car_dealer.domain.dto.Car;
import com.danicode.car_dealer.domain.repository.ICarRepository;
import com.danicode.car_dealer.domain.usecase.ICarUseCase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements ICarUseCase {

    private final ICarRepository repository;

    public CarServiceImpl(ICarRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Car> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Car> getByBrand(Long brandId) {
        return repository.getByBrand(brandId);
    }

    @Override
    public List<Car> getCarsByPriceLessThan(Double price) {
        return repository.getCarsByPriceLessThan(price);
    }

    @Override
    public Optional<Car> getCar(Long id) {
        return repository.getCar(id);
    }

    @Override
    public Car save(Car car) {
        return repository.save(car);
    }

    @Override
    public boolean delete(Long id) {
        if (repository.getCar(id).isEmpty()) {
            return false;
        }
        repository.delete(id);

        return true;
    }
}
