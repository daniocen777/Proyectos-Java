package com.danicode.blog.controller;

import com.danicode.blog.dto.ComentarioDTO;
import com.danicode.blog.service.IComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private IComentarioService comentarioService;

    @GetMapping("/{publicacionId}")
    public List<ComentarioDTO> listFeedbackById(@PathVariable long publicacionId) {
        return comentarioService.obtenerComentariosPorPublicacionId(publicacionId);
    }

    @GetMapping("/{publicacionId}/{comentarioId}")
    public ResponseEntity<ComentarioDTO> feedbackById(@PathVariable long publicacionId, @PathVariable long comentarioId) {
        ComentarioDTO comentarioDTO = comentarioService.obtenerComentarioPorId(publicacionId, comentarioId);
        return new ResponseEntity<>(comentarioDTO, HttpStatus.OK);
    }

    @PostMapping("/{publicacionId}")
    public ResponseEntity<ComentarioDTO> save(@PathVariable long publicacionId, @Valid @RequestBody ComentarioDTO comentarioDTO) {
        return new ResponseEntity<>(comentarioService.crear(publicacionId, comentarioDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{publicacionId}/{comentarioId}")
    public ResponseEntity<ComentarioDTO> updateById(@PathVariable Long publicacionId, @PathVariable Long comentarioId,
                                                    @Valid @RequestBody ComentarioDTO comentarioDTO) {

        return new ResponseEntity<>(comentarioService.actualizarComentario(publicacionId, comentarioId, comentarioDTO),
                HttpStatus.OK);
    }

    @DeleteMapping("/{publicacionId}/{comentarioId}")
    public ResponseEntity<String> delete(@PathVariable Long publicacionId, @PathVariable Long comentarioId) {
        comentarioService.eliminarComentario(publicacionId, comentarioId);
        return new ResponseEntity<>("Comentario eliminado", HttpStatus.OK);
    }
}
