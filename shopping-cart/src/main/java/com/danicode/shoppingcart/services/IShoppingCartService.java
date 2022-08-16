package com.danicode.shoppingcart.services;

import com.danicode.shoppingcart.entities.ShoppingCart;

import java.util.List;

public interface IShoppingCartService {

    List<ShoppingCart> getListByClient(String username);

    void cleanShoppingCart(String clientId);

    void removeProduct(String id);

    void addProduct(ShoppingCart shoppingCart);

    Long getCountByClient(String clientId);
}
