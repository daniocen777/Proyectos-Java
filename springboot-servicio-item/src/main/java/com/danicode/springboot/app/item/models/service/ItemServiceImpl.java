package com.danicode.springboot.app.item.models.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danicode.springboot.app.item.models.Item;
import com.danicode.springboot.app.item.models.Producto;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {

	// Cliente http
	@Autowired
	private RestTemplate clienteRest;

	@Override
	public List<Item> findAll() {
		// Obtener listado de producto (que esta en otro microservicio)
		List<Producto> productos = Arrays
				.asList(this.clienteRest.getForObject("http://localhost:8001/listar", Producto[].class));
		return productos.stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, int cantidad) {
		Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
		Producto producto = this.clienteRest.getForObject("http://localhost:8001/ver/{id}", Producto.class,
				pathVariables);
		
		return new Item(producto, cantidad);
	}

}
