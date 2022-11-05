package com.pe.test.pacientes.controller;

import com.pe.test.pacientes.entities.Parentesco;

import com.pe.test.pacientes.services.IParentescoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/parentesco")
public class ParentescoController {

    @Autowired
    private IParentescoService parentescoService;

    @GetMapping({"/", "", "/all"})
    public ResponseEntity<?> getAll() {
        Map<String, Object> response = new HashMap<>();
        List<Parentesco> parentesco = new ArrayList<>();
        try {
            parentesco = this.parentescoService.findAll();
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor 'ubigeo' (getAll): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Parentesco>>(parentesco, HttpStatus.OK);
    }
}
