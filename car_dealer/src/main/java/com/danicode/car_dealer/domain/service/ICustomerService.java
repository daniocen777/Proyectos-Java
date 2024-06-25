package com.danicode.car_dealer.domain.service;

import com.danicode.car_dealer.domain.dto.Brand;
import com.danicode.car_dealer.domain.dto.Customer;
import com.danicode.car_dealer.domain.dto.response.CustomerResponse;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    List<Customer> getAll();

    Optional<Customer> getCustomer(Long id);

    CustomerResponse save(Customer customer);

    Optional<Customer> getCustomerByEmail(String email);

    Optional<Customer> update(Customer customer);

    boolean delete(Long id);
}
