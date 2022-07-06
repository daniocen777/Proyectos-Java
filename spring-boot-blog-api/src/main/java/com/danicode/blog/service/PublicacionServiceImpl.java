package com.danicode.blog.service;

import com.danicode.blog.dto.PublicacionDTO;
import com.danicode.blog.entity.Publicacion;
import com.danicode.blog.repository.IPublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicacionServiceImpl implements IPublicacionService {

    @Autowired
    private IPublicacionRepository publicacionRepository;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        // convertir dto a entidad
        Publicacion publicacion = new Publicacion();
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());
        Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);
        // convertir entidad a dto
        PublicacionDTO publicacionResponse = new PublicacionDTO();
        publicacionResponse.setId(nuevaPublicacion.getId());
        publicacionResponse.setTitulo(nuevaPublicacion.getTitulo());
        publicacionResponse.setDescripcion(nuevaPublicacion.getDescripcion());
        publicacionResponse.setContenido(nuevaPublicacion.getContenido());

        return publicacionResponse;
    }
}
