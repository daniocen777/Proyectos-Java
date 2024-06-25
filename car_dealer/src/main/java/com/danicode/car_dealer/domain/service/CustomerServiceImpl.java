package com.danicode.car_dealer.domain.service;

import com.danicode.car_dealer.core.exception.EmailException;
import com.danicode.car_dealer.domain.dto.Customer;
import com.danicode.car_dealer.domain.dto.response.CustomerResponse;
import com.danicode.car_dealer.domain.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    private final ICustomerRepository repository;

    public CustomerServiceImpl(ICustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Customer> getAll() {
        return repository.getAll();
    }

    @Override
    public Optional<Customer> getCustomer(Long id) {
        return repository.getCustomer(id);
    }

    @Override
    public CustomerResponse save(Customer customer) {
        if (!customer.getEmail().matches("^(?=.{1,64}@)[A-Za-z0-9\\\\+_-]+(\\\\.[A-Za-z0-9\\\\+_-]+)*@"
                + "[^-][A-Za-z0-9\\\\+-]+(\\\\.[A-Za-z0-9\\\\+-]+)*(\\\\.[A-Za-z]{2,})$")) {
            throw new EmailException();
        }

        String passwordGenerated = generateRandomPassword(8);
        customer.setPassword(passwordGenerated);
        customer.setActive(true);

        repository.save(customer);

        return new CustomerResponse(passwordGenerated);
    }

    @Override
    public Optional<Customer> getCustomerByEmail(String email) {
        return repository.getCustomerByEmail(email);
    }

    @Override
    public Optional<Customer> update(Customer customer) {
        if (repository.getCustomer(customer.getId()).isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(repository.save(customer));
    }

    @Override
    public boolean delete(Long id) {
        if (repository.getCustomer(id).isEmpty()) {
            return false;
        }
        repository.delete(id);

        return true;
    }

    private String generateRandomPassword(int length) {
        final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lower = upper.toLowerCase();
        final String digits = "0123456789";
        final String specialChars = "!@#$%^&*()_+{}[]";
        final String chars = upper.concat(lower).concat(digits).concat(specialChars);

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }
}
