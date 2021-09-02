package com.danicode.springboot.app.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.danicode.springboot.app.item.models.Item;
import com.danicode.springboot.app.item.models.service.ItemService;

@RestController
public class ItemController {
	/*
	 * Existen dos implementaciones (la de Feign es la principal) Para saber cual se
	 * esta implementando, anotar con @Quelifier
	 */

	@Autowired
	@Qualifier("serviceFeign")
	private ItemService itemService;

	@GetMapping("/listar")
	public List<Item> listar() {
		return this.itemService.findAll();
	}

	@GetMapping("/ver/{id}/cantidad/{cantidad}")
	public Item detalle(@PathVariable Long id, @PathVariable int cantidad) {
		return this.itemService.findById(id, cantidad);
	}

}
