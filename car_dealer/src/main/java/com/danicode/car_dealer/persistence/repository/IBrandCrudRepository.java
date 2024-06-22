package com.danicode.car_dealer.persistence.repository;

import com.danicode.car_dealer.persistence.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBrandCrudRepository extends JpaRepository<BrandEntity, Long> {
}
