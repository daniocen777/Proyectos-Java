package com.danicode.shoppingcart.repositories;

import com.danicode.shoppingcart.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ISaleRepository extends JpaRepository<Sale, String> {

    @Transactional(readOnly = true)
    List<Sale> findByClient_Username(String username);

    @Transactional(readOnly = true)
    List<Sale> findByClient_Email(String email);
}
