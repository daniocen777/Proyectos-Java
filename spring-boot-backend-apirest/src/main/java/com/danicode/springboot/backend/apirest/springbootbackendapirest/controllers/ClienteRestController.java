package com.danicode.springboot.backend.apirest.springbootbackendapirest.controllers;

import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.entity.Cliente;
import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
    @Autowired
    private IClienteService clienteService;

    @GetMapping({"/", "", "/all"})
    public List<Cliente> index() {
        return this.clienteService.findAll();
    }

    @GetMapping("/{id}")
    public Cliente show(@PathVariable Long id) {
        return this.clienteService.findById(id);
    }

    @PostMapping({"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente store(@RequestBody Cliente cliente) {
        return this.clienteService.save(cliente);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente update(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteActual = this.clienteService.findById(id);
        clienteActual.setApellido(cliente.getApellido());
        clienteActual.setNombre(cliente.getNombre());
        clienteActual.setEmail(cliente.getEmail());
        return this.clienteService.save(clienteActual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id) {
        this.clienteService.delete(id);
    }
}
