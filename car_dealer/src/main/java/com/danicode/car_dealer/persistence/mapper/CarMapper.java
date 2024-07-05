package com.danicode.car_dealer.persistence.mapper;

import com.danicode.car_dealer.domain.dto.Car;
import com.danicode.car_dealer.persistence.entity.CarEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarMapper {
    Car toCar(CarEntity carEntity);

    @InheritInverseConfiguration
    CarEntity toCarEntity(Car car);

    List<Car> toCarsList(List<CarEntity> carEntities);
}
