package com.danicode.restaurantedata.main;

import java.sql.SQLException;
import java.util.List;

import com.danicode.restaurantedata.dao.TipoRestauranteDao;
import com.danicode.restaurantedata.dao.impl.TipoRestauranteDaoImpl;
import com.danicode.restaurantedata.entity.TipoRestaurante;

/*
 * Pruebas
 * */
public class Main {

	public static void main(String[] args) {
		TipoRestauranteDao tipoRestauranteDao = new TipoRestauranteDaoImpl();
//		TipoRestaurante tipoRestaurante = new TipoRestaurante();
//		tipoRestaurante.setDescripcion("China");
//		tipoRestaurante.setFechaCreacion(LocalDateTime.now());
//		tipoRestaurante.setEstatus(1);
		try {
			List<TipoRestaurante> resultado = tipoRestauranteDao.consultar();
			if (resultado.size() > 0) {
				TipoRestaurante tipoRestauranteDefoult = new TipoRestaurante();
				tipoRestauranteDefoult.setIdTipoRestaurante(0);
				tipoRestauranteDefoult.setDescripcion("No encontrado");
				tipoRestauranteDefoult.setIdTipoRestaurante(0);
				TipoRestaurante tipoRestaurante = resultado.stream()
						.filter(elemento -> elemento.getIdTipoRestaurante() == 4).findFirst()
						.orElse(tipoRestauranteDefoult);
				System.out.println(tipoRestaurante);
			} else {
				System.out.println("ERROR");
			}
		} catch (SQLException e) {
			System.out.println("ERROR => " + e.getMessage());
			e.printStackTrace();
		}

	}

}
