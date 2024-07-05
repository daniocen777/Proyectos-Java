package com.danicode.car_dealer.controller;

import com.danicode.car_dealer.domain.dto.Brand;
import com.danicode.car_dealer.domain.usecase.IBrandUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private final IBrandUseCase useCase;

    public BrandController(IBrandUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<List<Brand>> getAll() {
        return new ResponseEntity<>(useCase.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brand> getBrand(@PathVariable Long id) {
        return ResponseEntity.of(useCase.getBrand(id));
    }

    @PostMapping
    public ResponseEntity<Brand> save(@RequestBody Brand brand) {
        try {
            return new ResponseEntity<>(useCase.save(brand), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping
    public ResponseEntity<Brand> update(@RequestBody Brand brand) {
        return ResponseEntity.of(useCase.update(brand));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(useCase.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
