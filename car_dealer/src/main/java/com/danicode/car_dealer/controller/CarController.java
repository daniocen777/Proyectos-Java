package com.danicode.car_dealer.controller;

import com.danicode.car_dealer.domain.dto.Car;
import com.danicode.car_dealer.domain.usecase.ICarUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final ICarUseCase useCase;

    public CarController(ICarUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        return ResponseEntity.ok(useCase.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable Long id) {
        return ResponseEntity.of(useCase.getCar(id));
    }

    @GetMapping("/brand/{brandId}")
    public ResponseEntity<List<Car>> getCarByBrand(@PathVariable Long brandId) {
        return ResponseEntity.ok(useCase.getByBrand(brandId));
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<Car>> getCarsByPriceLessThan(@PathVariable Double price) {
        return ResponseEntity.ok(useCase.getCarsByPriceLessThan(price));
    }

    @PostMapping
    public ResponseEntity<Car> save(@RequestBody Car car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(useCase.save(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(useCase.delete(id) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
