package com.danicode.car_dealer.persistence.mapper;

import com.danicode.car_dealer.domain.dto.Brand;
import com.danicode.car_dealer.persistence.entity.BrandEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "description", target = "description")
    })
    Brand toBrand(BrandEntity brandEntity);

    @InheritInverseConfiguration
    @Mapping(target = "carEntities", ignore = true)
    BrandEntity toBrandEntity(Brand brand);

    List<Brand> toBrandsList(List<BrandEntity> brandEntities);
}
