package com.danicode.blog.controller;

import com.danicode.blog.dto.PublicacionDTO;
import com.danicode.blog.service.IPublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private IPublicacionService publicacionService;

    @PostMapping
    public ResponseEntity<PublicacionDTO> save(@RequestBody PublicacionDTO publicacionDTO) {
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public List<PublicacionDTO> findAll() {
        return publicacionService.obtenerPublicaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> findById(@PathVariable long id) {
        return ResponseEntity.ok(publicacionService.obtenerPublicacionPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> UpdateById(@PathVariable long id, @RequestBody PublicacionDTO publicacionDTO) {
        PublicacionDTO publicacionResponse = publicacionService.actualizarPublicacionPorId(id, publicacionDTO);
        return new ResponseEntity<>(publicacionResponse, HttpStatus.OK);
    }
}
