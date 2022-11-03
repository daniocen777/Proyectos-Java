package com.pe.test.pacientes.controller;

import com.pe.test.pacientes.entities.Paciente;

import com.pe.test.pacientes.services.PacienteMyBatisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteMyBatisRepository repository;

    @GetMapping({"/", "", "/all"})
    public ResponseEntity<?> getAll() {
        Map<String, Object> response = new HashMap<>();
        List<Paciente> paciente = new ArrayList<>();
        try {
            paciente = this.repository.findAll();
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor '/paciente' (getAll): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Paciente>>(paciente, HttpStatus.OK);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<?> insert(@RequestBody Paciente paciente) {
        int pacienteNuevo = 0;
        Map<String, Object> response = new HashMap<>();

        try {
            pacienteNuevo = this.repository.insert(paciente);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor (store): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("mensaje", "Paciente creado satisfactoriamente");
        response.put("cliente", pacienteNuevo);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
}
