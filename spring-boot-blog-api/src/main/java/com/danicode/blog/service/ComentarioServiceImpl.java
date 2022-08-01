package com.danicode.blog.service;

import com.danicode.blog.dto.ComentarioDTO;
import com.danicode.blog.entity.Comentario;
import com.danicode.blog.entity.Publicacion;
import com.danicode.blog.exception.BlogException;
import com.danicode.blog.exception.ResourceNotFoundException;
import com.danicode.blog.repository.IComentarioRepository;
import com.danicode.blog.repository.IPublicacionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImpl implements IComentarioService {
    // Para el mapeo
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IComentarioRepository comentarioRepository;

    @Autowired
    private IPublicacionRepository publicacionRepository;

    @Override
    public ComentarioDTO crear(long publicacionId, ComentarioDTO comentarioDTO) {
        Comentario comentario = mapearAEntidad(comentarioDTO);
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
        comentario.setPublicacion(publicacion);
        Comentario nuevoComentario = comentarioRepository.save(comentario);
        return mapearADTO(nuevoComentario);
    }

    @Override
    public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long publicacionId) {
        List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);
        return comentarios.stream().map(comentario -> mapearADTO(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDTO obtenerComentarioPorId(long publicacionId, long comentarioId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
        // buscar comentarios de la publicacion
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));
        if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogException(HttpStatus.BAD_REQUEST, "Comentario no pertenece a la publicación");
        }
        return mapearADTO(comentario);
    }

    @Override
    public ComentarioDTO actualizarComentario(Long publicacionId, Long comentarioId, ComentarioDTO comentarioDTO) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));
        if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogException(HttpStatus.BAD_REQUEST, "Comentario no pertenece a la publicación");
        }
        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setCuerpo(comentarioDTO.getCuerpo());
        Comentario comentarioActualizado = comentarioRepository.save(comentario);

        return mapearADTO(comentarioActualizado);
    }

    @Override
    public void eliminarComentario(Long publicacionId, Long comentarioId) {
        Publicacion publicacion = publicacionRepository.findById(publicacionId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", publicacionId));
        Comentario comentario = comentarioRepository.findById(comentarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Comentario", "id", comentarioId));
        if (!comentario.getPublicacion().getId().equals(publicacion.getId())) {
            throw new BlogException(HttpStatus.BAD_REQUEST, "Comentario no pertenece a la publicación");
        }

        comentarioRepository.delete(comentario);
    }

    private ComentarioDTO mapearADTO(Comentario comentario) {

        ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);

        return comentarioDTO;
    }

    private Comentario mapearAEntidad(ComentarioDTO comentarioDTO) {
        Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);

        return comentario;
    }
}
