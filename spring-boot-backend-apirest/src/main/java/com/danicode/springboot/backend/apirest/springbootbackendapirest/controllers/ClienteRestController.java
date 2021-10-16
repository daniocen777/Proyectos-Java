package com.danicode.springboot.backend.apirest.springbootbackendapirest.controllers;

import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.entity.Cliente;
import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> show(@PathVariable Long id) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();
        try {
            cliente = this.clienteService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor (show): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        if (cliente == null) {
            response.put("mensaje", "El cliente con ID: ".concat(id.toString()).concat(" no existe"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<?> store(@RequestBody Cliente cliente) {
        Cliente clienteNuevo = null;
        Map<String, Object> response = new HashMap<>();
        try {
            clienteNuevo = this.clienteService.save(cliente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor (store): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Cliente creado satisfactoriamente");
        response.put("cliente", clienteNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente clienteActual = this.clienteService.findById(id);
        Cliente clienteActualizado = null;
        Map<String, Object> response = new HashMap<>();

        if (clienteActual == null) {
            response.put("mensaje", "El cliente con ID: ".concat(id.toString()).concat(" no existe"));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setEmail(cliente.getEmail());

            clienteActualizado = this.clienteService.save(clienteActual);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor (update): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Cliente actualizado satisfactoriamente");
        response.put("cliente", clienteActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            this.clienteService.delete(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor (delete): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "Cliente eliminado satisfactoriamente");

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
}
