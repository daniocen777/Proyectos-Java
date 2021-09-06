package com.danicode.market.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.danicode.market.domain.Product;
import com.danicode.market.domain.repository.ProductRepository;
import com.danicode.market.persistence.crud.ProductoCrudRepository;
import com.danicode.market.persistence.entity.Producto;
import com.danicode.market.persistence.mapper.ProductMapper;

// Por buena practica, anotarla con @Repository

@Repository
public class ProductoRepository implements ProductRepository {

	@Autowired
	private ProductoCrudRepository productoCrudRepository;

	@Autowired
	private ProductMapper mapper;

	@Override
	public List<Product> getAll() {
		List<Producto> productos = (List<Producto>) this.productoCrudRepository.findAll();
		return this.mapper.toProducts(productos);
	}

	@Override
	public void delete(int productId) {
		this.productoCrudRepository.deleteById(productId);
	}

	@Override
	public Optional<List<Product>> getByCategory(int categoryId) {
		List<Producto> productos = this.productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
		return Optional.of(this.mapper.toProducts(productos));
	}

	@Override
	public Optional<List<Product>> getScarseProducts(int quantity) {
		Optional<List<Producto>> productos = this.productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,
				true);
		return productos.map(producto -> mapper.toProducts(producto));
	}

	@Override
	public Optional<Product> getProduct(int productId) {
		return this.productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));

	}

	@Override
	public Product save(Product product) {
		Producto producto = this.mapper.toProducto(product);
		return this.mapper.toProduct(this.productoCrudRepository.save(producto));

	}
}
