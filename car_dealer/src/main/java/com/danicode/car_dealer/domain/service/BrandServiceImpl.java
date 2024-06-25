package com.danicode.car_dealer.domain.service;

import com.danicode.car_dealer.domain.dto.Brand;
import com.danicode.car_dealer.domain.repository.IBrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements IBrandService {

    private final IBrandRepository brandRepository;

    // Por defecto, el Autowired esta implementado. Solo cuando tiene un constructor
    public BrandServiceImpl(IBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAll() {
        return brandRepository.getAll();
    }

    @Override
    public Optional<Brand> getBrand(Long id) {
        return brandRepository.getBrand(id);
    }

    @Override
    public Brand save(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Optional<Brand> update(Brand brand) {
        if (brandRepository.getBrand(brand.getId()).isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(brandRepository.save(brand));
    }

    @Override
    public boolean delete(Long id) {
        if (brandRepository.getBrand(id).isEmpty()) {
            return false;
        }

        brandRepository.delete(id);

        return true;
    }
}
