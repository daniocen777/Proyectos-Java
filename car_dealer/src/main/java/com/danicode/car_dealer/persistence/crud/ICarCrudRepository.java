package com.danicode.car_dealer.persistence.crud;

import com.danicode.car_dealer.persistence.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICarCrudRepository extends JpaRepository<CarEntity, Long> {

    List<CarEntity> findByBrandId(Long brandId);

    List<CarEntity> findByPriceLessThanEqualOrderByPriceDesc(Double price);
}
