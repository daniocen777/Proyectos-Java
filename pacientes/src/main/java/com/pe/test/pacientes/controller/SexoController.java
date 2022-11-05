package com.pe.test.pacientes.controller;

import com.pe.test.pacientes.entities.Sexo;
import com.pe.test.pacientes.services.ISexoService;
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
@RequestMapping("/sexo")
public class SexoController {

    @Autowired
    private ISexoService sexoService;

    @GetMapping({"/", "", "/all"})
    public ResponseEntity<?> getAll() {
        Map<String, Object> response = new HashMap<>();
        List<Sexo> sexo = new ArrayList<>();
        try {
            sexo = this.sexoService.findAll();
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor '/sexo' (getAll): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<Sexo>>(sexo, HttpStatus.OK);
    }
}
