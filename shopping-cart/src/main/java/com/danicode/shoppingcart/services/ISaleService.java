package com.danicode.shoppingcart.services;

import com.danicode.shoppingcart.entities.Sale;

import java.util.List;

public interface ISaleService {

    List<Sale> getSalesByClient(String username);

    void createSale(String username);


}
