package com.danicode.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.danicode.springboot.app.models.entity.Cliente;
import com.danicode.springboot.app.models.services.IClienteService;

/*
 * @SessionAttributes => Se guarda el objeto cliente (se mapea al formulario). 
 * Pero al crear nuevo cliente, se tiene que crear otra sesión (SessionStatus en complete)
 * */

@Controller
@SessionAttributes("cliente")
public class ClienteController {

	// Inyectando servicios
	@Autowired
	private IClienteService clienteService;

	@GetMapping({ "/", "", "/listar" })
	public String listar(Model model) {
		model.addAttribute("titulo", "Listado De Clientes");
		model.addAttribute("clientes", clienteService.findAll());
		return "listar";
	}

	// Ir al formulario
	@GetMapping("/form")
	public String crear(Model model) {
		Cliente cliente = new Cliente();
		model.addAttribute("titulo", "Formulario De Registro");
		model.addAttribute("cliente", cliente);
		return "form";
	}

	// Procesar || para validar con la entidad Cliente, colocar @Valid y agregar
	// objeto de tipo BindingResult (La clase validada y el objeto de tipo
	// BindingResult van juntos, luego va los demas objetos)
	@PostMapping("/form")
	public String crear(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario De Registro");
			return "form";
		}

		clienteService.save(cliente);
		status.setComplete();
		return "redirect:/listar";
	}

	// Obtener por id
	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findOne(id);
		} else {
			return "redirect:/listar";
		}
		model.addAttribute("titulo", "Editar Cliente");
		model.addAttribute("cliente", cliente);
		return "form";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, Model model) {
		if (id > 0) {
			clienteService.delete(id);
		}
		
		return "redirect:/listar";
	}

}
