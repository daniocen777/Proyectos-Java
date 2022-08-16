package com.danicode.shoppingcart.services;

import com.danicode.shoppingcart.entities.Product;
import com.danicode.shoppingcart.repositories.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductRepository productRepository;

    @Override
    public void createProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getRelatedProducts(String category, String id) {
        List<Product> productList = this.productRepository.findByCategoryAndIdNot(category, id);
        List<Product> randomProducts = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            int randomIndex = random.nextInt(productList.size());
            randomProducts.add(productList.get(randomIndex));
            productList.remove(randomIndex);
        }
        return randomProducts;
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return this.productRepository.findById(id);
    }

    @Override
    public List<Product> getBestPriceProduct() {
        return productRepository.findFirst4ByOrderByPriceAsc();
    }
}
