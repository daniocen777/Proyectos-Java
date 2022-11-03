package com.pe.test.pacientes.controller;

import com.pe.test.pacientes.entities.TipoDocumentoIdentidad;
import com.pe.test.pacientes.services.TipoDocumentoIdentidadMyBatisRepository;
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
@RequestMapping("/tipoDocumento")
public class TipoDocumentoIdentidadController {

    @Autowired
    private TipoDocumentoIdentidadMyBatisRepository repository;

    @GetMapping({"/", "", "/all"})
    public ResponseEntity<?> getAll() {
        Map<String, Object> response = new HashMap<>();
        List<TipoDocumentoIdentidad> tipoDocumento = new ArrayList<>();
        try {
            tipoDocumento = this.repository.findAll();
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor '/tipoDocumento' (getAll): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<TipoDocumentoIdentidad>>(tipoDocumento, HttpStatus.OK);
    }
}
