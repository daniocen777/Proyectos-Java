package com.danicode.market.persistence.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.danicode.market.persistence.entity.Producto;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {
	// Productos por categoria
	public List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

	public Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidadStock, boolean estado);
}
