package com.danicode.blog.service;

import com.danicode.blog.dto.PublicacionDTO;
import com.danicode.blog.dto.PublicacionResponse;

public interface IPublicacionService {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    public PublicacionResponse obtenerPublicaciones(int page, int size, String sortBy, String sortDir);

    public PublicacionDTO obtenerPublicacionPorId(long id);

    public PublicacionDTO actualizarPublicacionPorId(long id, PublicacionDTO publicacionDTO);

    public void eliminarPublicacionPorId(long id);
}
