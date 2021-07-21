package com.danicode.restaurantedata.dao;

import java.sql.SQLException;
import java.util.List;

import com.danicode.restaurantedata.entity.TipoRestaurante;
import com.danicode.restaurantedata.myexceptions.RestauranteException;

public interface TipoRestauranteDao {
	// Cantidad de registro guardados | return 1 ok | return 0 NoOk
	int guardar(TipoRestaurante tipoRestaurante) throws SQLException;

	int actualizar(TipoRestaurante tipoRestaurante) throws SQLException;

	int eliminar(int id) throws SQLException;

	List<TipoRestaurante> consultar() throws SQLException;

	TipoRestaurante consultarPorId(int id) throws SQLException, RestauranteException;
}
