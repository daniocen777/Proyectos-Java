package com.danicode.restaurantedata.dao;

import java.sql.SQLException;
import java.util.List;

import com.danicode.restauranteentities.entity.Restaurante;

public interface RestauranteDao {
	int guardar(Restaurante restaurante) throws SQLException;

	int actualizar(Restaurante restaurante) throws SQLException;

	int eliminar(int id) throws SQLException;

	List<Restaurante> consultar() throws SQLException;

	Restaurante consultarPorId(int id) throws SQLException;
}
