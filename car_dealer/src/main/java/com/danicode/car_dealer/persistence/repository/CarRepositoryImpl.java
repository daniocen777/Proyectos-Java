package com.danicode.car_dealer.persistence.repository;

import com.danicode.car_dealer.domain.dto.Car;
import com.danicode.car_dealer.domain.repository.ICarRepository;
import com.danicode.car_dealer.persistence.crud.ICarCrudRepository;
import com.danicode.car_dealer.persistence.entity.CarEntity;
import com.danicode.car_dealer.persistence.mapper.CarMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CarRepositoryImpl implements ICarRepository {

    private final ICarCrudRepository repository;
    private final CarMapper mapper;

    public CarRepositoryImpl(ICarCrudRepository repository, CarMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Car> getAll() {
        return mapper.toCarsList(repository.findAll());
    }

    @Override
    public List<Car> getByBrand(Long brandId) {
        return mapper.toCarsList(repository.findByBrandId(brandId));
    }

    @Override
    public List<Car> getCarsByPriceLessThan(Double price) {
        return mapper.toCarsList(repository.findByPriceLessThanEqualOrderByPriceDesc(price));
    }

    @Override
    public Optional<Car> getCar(Long id) {
        return repository.findById(id)
                .map(mapper::toCar);
    }

    @Override
    public Car save(Car car) {
        System.out.println("CarRepositoryImpl save CAR: " + car);
        CarEntity carEntity = mapper.toCarEntity(car);
        System.out.println("CarRepositoryImpl save CarEntity: " + carEntity);
        return mapper.toCar(repository.save(carEntity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
