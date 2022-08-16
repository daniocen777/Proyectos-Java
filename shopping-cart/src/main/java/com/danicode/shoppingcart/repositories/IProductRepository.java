package com.danicode.shoppingcart.repositories;

import com.danicode.shoppingcart.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, String> {

    @Transactional(readOnly = true)
    List<Product> findByCategoryAndIdNot(String category, String id);

    @Transactional(readOnly = true)
    List<Product> findFirst4ByOrderByPriceAsc();
}
