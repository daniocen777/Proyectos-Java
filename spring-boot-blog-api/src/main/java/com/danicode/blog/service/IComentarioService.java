package com.danicode.blog.service;

import com.danicode.blog.dto.ComentarioDTO;

import java.util.List;

public interface IComentarioService {

    public ComentarioDTO crear(long publicacionId, ComentarioDTO comentarioDTO);

    public List<ComentarioDTO> obtenerComentariosPorPublicacionId(long publicacionId);

    public ComentarioDTO obtenerComentarioPorId(long publicacionId, long comentarioId);

    public ComentarioDTO actualizarComentario(Long publicacionId, Long comentarioId, ComentarioDTO comentarioDTO);

    public void eliminarComentario(Long publicacionId, Long comentarioId);
}
