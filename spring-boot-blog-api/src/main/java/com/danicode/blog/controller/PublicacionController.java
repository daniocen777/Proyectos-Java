package com.danicode.blog.controller;

import com.danicode.blog.dto.PublicacionDTO;
import com.danicode.blog.dto.PublicacionResponse;
import com.danicode.blog.service.IPublicacionService;
import com.danicode.blog.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {

    @Autowired
    private IPublicacionService publicacionService;

    @GetMapping
    public PublicacionResponse findAll(@RequestParam(value = "page", defaultValue = Constants.PAGE_DEFAULT, required = false) int page,
                                       @RequestParam(value = "size", defaultValue = Constants.SIZE_DEFAULT, required = false) int size,
                                       @RequestParam(value = "sortBy", defaultValue = Constants.SORT_BY, required = false) String sortBy,
                                       @RequestParam(value = "sortDir", defaultValue = Constants.SORT_DIR, required = false) String sortDir) {

        return publicacionService.obtenerPublicaciones(page, size, sortBy, sortDir);

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PublicacionDTO> save(@Valid @RequestBody PublicacionDTO publicacionDTO) {
        return new ResponseEntity<>(publicacionService.crearPublicacion(publicacionDTO), HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PublicacionDTO> findById(@PathVariable long id) {
        return ResponseEntity.ok(publicacionService.obtenerPublicacionPorId(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PublicacionDTO> UpdateById(@PathVariable long id, @Valid @RequestBody PublicacionDTO publicacionDTO) {
        PublicacionDTO publicacionResponse = publicacionService.actualizarPublicacionPorId(id, publicacionDTO);
        return new ResponseEntity<>(publicacionResponse, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        publicacionService.eliminarPublicacionPorId(id);
        return new ResponseEntity<>("Publicación eliminada", HttpStatus.OK);
    }
}