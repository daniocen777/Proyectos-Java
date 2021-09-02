package com.danicode.springboot.app.productos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.danicode.springboot.app.productos.models.entity.Producto;
import com.danicode.springboot.app.productos.models.service.IProductoService;

// @RestController > Convierte los datos de retorno a json
@RestController
public class ProductoController {

	@Autowired
	private IProductoService productoService;

	@GetMapping("/listar")
	public List<Producto> listar() {
		return this.productoService.findAll();
	}

	@GetMapping("/ver/{id}")
	public Producto detalle(@PathVariable Long id) {
		return this.productoService.findById(id);
	}
}
