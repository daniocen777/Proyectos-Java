package com.danicode.blog.service;

import com.danicode.blog.dto.PublicacionDTO;

import java.util.List;

public interface IPublicacionService {

    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO);

    public List<PublicacionDTO> obtenerPublicaciones();

    public PublicacionDTO obtenerPublicacionPorId(long id);

    public PublicacionDTO actualizarPublicacionPorId(long id, PublicacionDTO publicacionDTO);
}
