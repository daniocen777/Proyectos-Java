package com.danicode.restaurantedata.dao.impl.test;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.danicode.restaurantedata.dao.RestauranteDao;
import com.danicode.restaurantedata.dao.impl.RestauranteDaoImpl;
import com.danicode.restaurantedata.entity.Restaurante;

class RestauranteDaoImplTest {

	@Test
	void testGuardar() {
		RestauranteDao restauranteDao = new RestauranteDaoImpl();
		Restaurante restaurante = new Restaurante();
		restaurante.setNombre("Pasa Chito");
		restaurante.setImagen("chito.png");
		restaurante.setEstatus(1);
		restaurante.setFechaCreacion(LocalDateTime.now());
	}

}
