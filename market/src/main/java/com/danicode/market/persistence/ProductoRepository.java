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
		return mapper.toProducts(productos);
	}

	@Override
	public void delete(int productId) {
		productoCrudRepository.deleteById(productId);
	}

	@Override
	public Optional<List<Product>> getByCategory(int categoryId) {
		List<Producto> productos = this.productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
		return Optional.of(mapper.toProducts(productos));
	}

	@Override
	public Optional<List<Product>> getScarseProducts(int quantity) {
		Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity,
				true);
		return productos.map(producto -> mapper.toProducts(producto));
	}

	@Override
	public Optional<Product> getProduct(int productId) {
		return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));

	}

	@Override
	public Product save(Product product) {
		Producto producto = mapper.toProducto(product);
		return mapper.toProduct(productoCrudRepository.save(producto));
	}
}
