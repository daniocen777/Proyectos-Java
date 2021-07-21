package com.danicode.restaurantedata.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.danicode.restaurantedata.connection.ConnectionFactory;
import com.danicode.restaurantedata.dao.RestauranteDao;
import com.danicode.restaurantedata.entity.Restaurante;

public class RestauranteDaoImpl implements RestauranteDao {

	static {
		try {
			ConnectionFactory.conectar();
		} catch (ClassNotFoundException e) {
			System.out.print("ERROR DE BD ClassNotFoundException => " + e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.print("ERROR DE BD SQLException => " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public int guardar(Restaurante restaurante) throws SQLException {
		String sql = "INSERT INTO restaurante (nombre, imagen, slogan, idTipoRestaurante, fechaCreacion, estatus, idMenu) VALUES('"
				+ restaurante.getNombre() + "', '" + restaurante.getImagen() + "', '" + restaurante.getSlogan() + "', '"
				+ restaurante.getTipoRestaurante().getIdTipoRestaurante() + "', '" + restaurante.getFechaCreacion()
				+ "', '" + restaurante.isEstatus() + "', '" + restaurante.getMenu().getIdMenu() + "')";
		int resultado = ConnectionFactory.ejecutarSql(sql);
		return resultado;
	}

	@Override
	public int actualizar(Restaurante restaurante) throws SQLException {

		return 0;
	}

	@Override
	public int eliminar(int id) throws SQLException {

		return 0;
	}

	@Override
	public List<Restaurante> consultar() throws SQLException {

		return null;
	}

	@Override
	public Restaurante consultarPorId(int id) throws SQLException {

		return null;
	}

}
