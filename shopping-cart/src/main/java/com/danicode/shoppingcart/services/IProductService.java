package com.danicode.shoppingcart.services;

import com.danicode.shoppingcart.entities.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    void createProduct(Product product);

    List<Product> getAllProducts();

    // Productos relacionados por "category" o "id". Devuelve 2 productos al azar
    List<Product> getRelatedProducts(String category, String id);

    Optional<Product> getProductById(String id);

    List<Product> getBestPriceProduct();
}
