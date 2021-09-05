package com.danicode.market.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.danicode.market.persistence.crud.ProductoCrudRepository;
import com.danicode.market.persistence.entity.Producto;

// Por buena practica, anotarla con @Repository

@Repository
public class ProductoRepository {

	private ProductoCrudRepository productoCrudRepository;

	public List<Producto> getAll() {
		return (List<Producto>) this.productoCrudRepository.findAll();
	}

	public List<Producto> getByCategoria(int idCategoria) {
		return this.productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
	}

	public Optional<List<Producto>> getProductosEscasos(int cantidadStock, boolean estado) {
		return this.productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidadStock, true);
	}

	public Optional<Producto> getProducto(int id) {
		return this.productoCrudRepository.findById(id);
	}

	public Producto save(Producto producto) {
		return this.productoCrudRepository.save(producto);
	}

	public void delete(int id) {
		this.productoCrudRepository.deleteById(id);
	}
}
