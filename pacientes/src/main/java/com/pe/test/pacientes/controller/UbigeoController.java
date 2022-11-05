package com.pe.test.pacientes.controller;

import com.pe.test.pacientes.entities.Ubigeo;
import com.pe.test.pacientes.services.IUbigeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ubigeo")
public class UbigeoController {

    @Autowired
    private IUbigeoService ubigeoService;

    @GetMapping({"/", "", "/all"})
    public ResponseEntity<?> getAll() {
        Map<String, Object> response = new HashMap<>();
        List<Ubigeo> ubigeo = new ArrayList<>();
        try {
            ubigeo = this.ubigeoService.findAll();
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor '/ubigeo' (getAll): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<Ubigeo>>(ubigeo, HttpStatus.OK);
    }

    @GetMapping({"/departamento",})
    public ResponseEntity<?> getByDepartment(@RequestParam String dep) {
        Map<String, Object> response = new HashMap<>();
        List<Ubigeo> ubigeo = new ArrayList<>();
        List<Ubigeo> filter = new ArrayList<>();
        try {
            ubigeo = this.ubigeoService.findAll();
            filter = ubigeo.stream().filter(u -> u.getCodigoDepartamento().equalsIgnoreCase(dep)).collect(Collectors.toList());
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor '/departamento?' (getByDepartment): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (filter.isEmpty()) {
            response.put("mensaje", "no existe el código de departmento: " + dep);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Ubigeo>>(filter, HttpStatus.OK);
    }

    @GetMapping({"/provincia",})
    public ResponseEntity<?> getByDepartmentAndProv(@RequestParam String dep, @RequestParam String pro) {
        Map<String, Object> response = new HashMap<>();
        List<Ubigeo> ubigeo = new ArrayList<>();
        List<Ubigeo> filter = new ArrayList<>();
        try {
            ubigeo = this.ubigeoService.findAll();
            filter = ubigeo.stream().
                    filter(u -> u.getCodigoDepartamento().equalsIgnoreCase(dep))
                    .filter(u -> u.getCodigoProvincia().equalsIgnoreCase(pro))
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            response.put("mensaje", "Error de servidor '/provincia?' (getByDepartmentAndProv): *** "
                    .concat(e.getMessage())
                    .concat(" *** :")
                    .concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if (filter.isEmpty()) {
            response.put("mensaje", "no existe departamento o provincia");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Ubigeo>>(filter, HttpStatus.OK);
    }
}