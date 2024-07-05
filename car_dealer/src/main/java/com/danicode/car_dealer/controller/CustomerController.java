package com.danicode.car_dealer.controller;

import com.danicode.car_dealer.domain.dto.Customer;
import com.danicode.car_dealer.domain.dto.response.CustomerResponse;
import com.danicode.car_dealer.domain.usecase.ICustomerUseCase;
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
@RequestMapping("/customers")
public class CustomerController {

    private final ICustomerUseCase useCase;

    public CustomerController(ICustomerUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll() {
        return ResponseEntity.ok(useCase.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        return ResponseEntity.of(useCase.getCustomer(id));
    }

    @GetMapping("/find_by_email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        return ResponseEntity.of(useCase.getCustomerByEmail(email));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> save(@RequestBody Customer customer) {
        return new ResponseEntity<>(useCase.save(customer), HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Customer> update(@RequestBody Customer customer) {
        return ResponseEntity.of(useCase.update(customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return new ResponseEntity<>(useCase.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
