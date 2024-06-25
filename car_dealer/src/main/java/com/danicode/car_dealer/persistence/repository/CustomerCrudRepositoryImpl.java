package com.danicode.car_dealer.persistence.repository;

import com.danicode.car_dealer.domain.dto.Customer;
import com.danicode.car_dealer.domain.repository.ICustomerRepository;
import com.danicode.car_dealer.persistence.entity.CustomerEntity;
import com.danicode.car_dealer.persistence.mapper.ICustomerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerCrudRepositoryImpl implements ICustomerRepository {

    private final ICustomerCrudRepository repository;

    private final ICustomerMapper mapper;

    public CustomerCrudRepositoryImpl(ICustomerCrudRepository repository, ICustomerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<Customer> getAll() {
        return mapper.toCustomersList(repository.findAll());
    }

    @Override
    public Optional<Customer> getCustomer(Long id) {
        return repository.findById(id).map(mapper::toCustomer);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = mapper.toCustomerEntity(customer);

        return mapper.toCustomer(repository.save(customerEntity));
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return repository.findByEmail(email).map(mapper::toCustomer);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
