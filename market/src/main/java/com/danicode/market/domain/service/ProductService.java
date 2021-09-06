package com.danicode.market.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danicode.market.domain.Product;
import com.danicode.market.domain.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAll() {
		return this.productRepository.getAll();
	}

	public Optional<Product> getProduct(int productId) {
		return this.productRepository.getProduct(productId);
	}

	public Optional<List<Product>> getByCategory(int categoryId) {
		return this.productRepository.getByCategory(categoryId);
	}

	public Product save(Product product) {
		return this.productRepository.save(product);
	}

	public boolean delete(int productId) {
		return getProduct(productId).map(product -> {
			this.productRepository.delete(productId);
			return true;
		}).orElse(false);
	}
}
