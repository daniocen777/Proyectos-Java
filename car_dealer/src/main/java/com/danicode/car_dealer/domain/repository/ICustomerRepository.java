package com.danicode.car_dealer.domain.repository;

import com.danicode.car_dealer.domain.dto.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository {
    List<Customer> getAll();

    Optional<Customer> getCustomer(Long id);

    Customer save(Customer customer);

    Optional<Customer> getCustomerByEmail(String email);

    void delete(Long id);
}
