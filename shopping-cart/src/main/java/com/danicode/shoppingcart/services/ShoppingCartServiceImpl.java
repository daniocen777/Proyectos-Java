package com.danicode.shoppingcart.services;

import com.danicode.shoppingcart.entities.ShoppingCart;
import com.danicode.shoppingcart.repositories.IShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ShoppingCartServiceImpl implements IShoppingCartService {

    @Autowired
    private IShoppingCartRepository shoppingCartRepository;

    @Override
    public List<ShoppingCart> getListByClient(String username) {
        return shoppingCartRepository.findByClient_Username(username);
    }

    @Override
    public void cleanShoppingCart(String clientId) {
        shoppingCartRepository.deleteByClient_Id(clientId);
    }

    @Override
    public void removeProduct(String id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public void addProduct(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public Long getCountByClient(String clientId) {
        return shoppingCartRepository.countByClient_Id(clientId);
    }
}
