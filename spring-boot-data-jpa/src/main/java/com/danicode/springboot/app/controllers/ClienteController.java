package com.danicode.springboot.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		model.addAttribute("titulo", "Listado De Clientes");
		model.addAttribute("clientes", clientes);
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
	public String crear(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario De Registro");
			return "form";
		}
		String mensaje = (cliente.getId() != null) ? "Cliente editado exitosamente" : "Cliente creado exitosamente";

		clienteService.save(cliente);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/listar";
	}

	// Obtener por id
	@GetMapping("/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		Cliente cliente = null;
		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El ID del cliente no existe");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El ID del cliente no puede ser cero (0)");
			return "redirect:/listar";
		}
		model.addAttribute("titulo", "Editar Cliente");
		model.addAttribute("cliente", cliente);
		return "form";
	}

	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, Model model, RedirectAttributes flash) {
		if (id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("info", "Cliente eliminado exitosamente");
		}

		return "redirect:/listar";
	}

}
