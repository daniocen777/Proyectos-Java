package com.danicode.market.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danicode.market.domain.Product;
import com.danicode.market.domain.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/all")
	public List<Product> getAll() {
		return this.productService.getAll();
	}

	@GetMapping("/{id}")
	public Optional<Product> getProduct(@PathVariable("id") int productId) {
		return this.productService.getProduct(productId);
	}

	@GetMapping("/category/{categoryId}")
	public Optional<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
		return this.productService.getByCategory(categoryId);
	}

	@PostMapping("/save")
	public Product save(@RequestBody Product product) {
		return this.productService.save(product);
	}

	@DeleteMapping("/delete/{id}")
	public boolean delete(@PathVariable("id") int productId) {
		return this.productService.delete(productId);
	}
}
