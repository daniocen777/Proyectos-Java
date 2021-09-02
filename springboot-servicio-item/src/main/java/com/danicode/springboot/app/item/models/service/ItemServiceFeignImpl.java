package com.danicode.springboot.app.item.models.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.danicode.springboot.app.item.clientes.ProductoClienteRest;
import com.danicode.springboot.app.item.models.Item;

/*Como se tiene dos implementaciones (uno con RestTemplate y otra con Feign)
 * Para indicar la principal, usar anotacion @Primary
 * */

@Service("serviceFeign")
@Primary
public class ItemServiceFeignImpl implements ItemService {

	@Autowired
	private ProductoClienteRest clienteFeign;

	@Override
	public List<Item> findAll() {

		return this.clienteFeign.listar().stream().map(producto -> new Item(producto, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, int cantidad) {
		return new Item(this.clienteFeign.detalle(id), cantidad);
	}

}
