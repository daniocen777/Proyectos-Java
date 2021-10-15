package com.danicode.springboot.backend.apirest.springbootbackendapirest.controllers;

import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.entity.Cliente;
import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {
    @Autowired
    private IClienteService clienteService;

    @GetMapping("")
    public List<Cliente> index() {
        return clienteService.findAll();
    }
}
