package com.danicode.restaurantedata.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.danicode.restaurantedata.connection.ConnectionFactory;
import com.danicode.restaurantedata.dao.TipoRestauranteDao;
import com.danicode.restaurantedata.entity.TipoRestaurante;
import com.danicode.restaurantedata.enums.CodigoEnum;
import com.danicode.restaurantedata.myexceptions.RestauranteException;

public class TipoRestauranteDaoImpl implements TipoRestauranteDao {

	/*
	 * static => Bloque que ejecuta sentencias cuando se está cargando la clase
	 * luego de generarse una instancia de la misma
	 */

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
	public int guardar(TipoRestaurante tipoRestaurante) throws SQLException {
		String sql = "INSERT INTO tipo_restaurante (descripcion, fechaCreacion, estatus) VALUES('"
				+ tipoRestaurante.getDescripcion() + "', '" + tipoRestaurante.getFechaCreacion() + "', '"
				+ tipoRestaurante.isEstatus() + "')";
		int resultado = ConnectionFactory.ejecutarSql(sql);
		return resultado;
	}

	@Override
	public int actualizar(TipoRestaurante tipoRestaurante) throws SQLException {
		String sql = "UPDATE tipo_restaurante SET  descripcion = '" + tipoRestaurante.getDescripcion()
				+ "', fechaCreacion = '" + tipoRestaurante.getFechaModificacion() + "', estatus = '"
				+ tipoRestaurante.isEstatus() + "' WHERE idTipoRestaurante = '" + tipoRestaurante.getIdTipoRestaurante()
				+ "'";
		int resultado = ConnectionFactory.ejecutarSql(sql);
		return resultado;
	}

	@Override
	public int eliminar(int id) throws SQLException {
		String sql = "DELETE FROM tipo_restaurante WHERE idTipoRestaurante = '" + id + "'";
		int resultado = ConnectionFactory.ejecutarSql(sql);
		return resultado;
	}

	@Override
	public List<TipoRestaurante> consultar() throws SQLException {
		String sql = "SELECT idTipoRestaurante, descripcion, fechaCreacion, fechaModificacion, estatus FROM tipo_restaurante ORDER BY descripcion";
		List<TipoRestaurante> list = new ArrayList<TipoRestaurante>();
		ResultSet rs = ConnectionFactory.ejecutarSqlSelect(sql);
		if (rs != null) {
			while (rs.next()) {
				TipoRestaurante tipoRestaurante = new TipoRestaurante();
				tipoRestaurante.setIdTipoRestaurante(rs.getInt("idTipoRestaurante"));
				tipoRestaurante.setDescripcion(rs.getString("descripcion"));
				tipoRestaurante.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				tipoRestaurante.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null
						? rs.getTimestamp("fechaModificacion").toLocalDateTime()
						: null);
				tipoRestaurante.setEstatus(rs.getInt("estatus"));
				list.add(tipoRestaurante);
			}
		}

		return list;
	}

	@Override
	public TipoRestaurante consultarPorId(int id) throws SQLException, RestauranteException {
		String sql = "SELECT idTipoRestaurante, descripcion, fechaCreacion, fechaModificacion, estatus FROM tipo_restaurante WHERE idTipoRestaurante = '"
				+ id + "'";
		ResultSet rs = null;

		try {
			rs = ConnectionFactory.ejecutarSqlSelect(sql);
		} catch (Exception e) {
			throw new RestauranteException("Error de sintaxis en la sentenia " + sql,
					CodigoEnum.SINTAXIS_SQL_ERROR_CODE);
		}

		TipoRestaurante tipoRestaurante = null;
		if (rs != null) {
			if (rs.next()) {
				tipoRestaurante = new TipoRestaurante();
				tipoRestaurante.setIdTipoRestaurante(rs.getInt("idTipoRestaurante"));
				tipoRestaurante.setDescripcion(rs.getString("descripcion"));
				tipoRestaurante.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
				tipoRestaurante.setFechaModificacion(rs.getTimestamp("fechaModificacion") != null
						? rs.getTimestamp("fechaModificacion").toLocalDateTime()
						: null);
				tipoRestaurante.setEstatus(rs.getInt("estatus"));
			}
		}
		return tipoRestaurante;
	}
}
