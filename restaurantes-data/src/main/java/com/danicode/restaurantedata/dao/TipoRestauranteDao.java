package com.danicode.restaurantedata.dao;

import java.sql.SQLException;
import java.util.List;

import com.danicode.restaurantedata.myexceptions.RestauranteException;
import com.danicode.restauranteentities.entity.TipoRestaurante;

public interface TipoRestauranteDao {
	// Cantidad de registro guardados | return 1 ok | return 0 NoOk
	String guardar(TipoRestaurante tipoRestaurante);

	int actualizar(TipoRestaurante tipoRestaurante);

	int eliminar(int id) throws SQLException;

	List<TipoRestaurante> consultar() ;

	TipoRestaurante consultarPorId(int id);
}
