package com.danicode.springboot.app.item.clientes;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.danicode.springboot.app.item.models.Producto;

/*
 * Clientes Rest con Feign
 * */

@FeignClient(name = "servicio-productos")
public interface ProductoClienteRest {

	// Los endpoints deben ser iguales al servicio producto en su controlador
	@GetMapping("/listar")
	public List<Producto> listar();

	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id);
}
