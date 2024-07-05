package com.danicode.car_dealer.persistence.repository;

import com.danicode.car_dealer.domain.dto.Brand;
import com.danicode.car_dealer.domain.repository.IBrandRepository;
import com.danicode.car_dealer.persistence.crud.IBrandCrudRepository;
import com.danicode.car_dealer.persistence.entity.BrandEntity;
import com.danicode.car_dealer.persistence.mapper.IBrandMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BrandRepositoryImpl implements IBrandRepository {

    private final IBrandCrudRepository brandCrudRepository;

    private final IBrandMapper brandMapper;

    // Por defecto, el Autowired esta implementado. Solo cuando tiene un constructor
    public BrandRepositoryImpl(IBrandCrudRepository brandCrudRepository, IBrandMapper brandMapper) {
        this.brandCrudRepository = brandCrudRepository;
        this.brandMapper = brandMapper;
    }

    @Override
    public List<Brand> getAll() {
        return brandMapper.toBrandsList(brandCrudRepository.findAll());
    }

    @Override
    public Optional<Brand> getBrand(Long id) {
        return brandCrudRepository.findById(id)
                .map(brandMapper::toBrand);
    }

    @Override
    public Brand save(Brand brand) {
        BrandEntity brandEntity = brandMapper.toBrandEntity(brand);

        return brandMapper.toBrand(brandCrudRepository.save(brandEntity));
    }

    @Override
    public void delete(Long id) {
        brandCrudRepository.deleteById(id);
    }
}
