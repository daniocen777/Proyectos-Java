package com.danicode.shoppingcart.repositories;

import com.danicode.shoppingcart.entities.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IShoppingCartRepository extends JpaRepository<ShoppingCart, String> {

    List<ShoppingCart> findByClient_Id(String id);

    List<ShoppingCart> findByClient_Username(String username);

    List<ShoppingCart> findByClient_Email(String email);

    void deleteByClient_Id(String id);

    Long countByClient_Id(String id);
}
