package com.danicode.blog.service;

import com.danicode.blog.dto.PublicacionDTO;
import com.danicode.blog.entity.Publicacion;
import com.danicode.blog.exception.ResourceNotFoundException;
import com.danicode.blog.repository.IPublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceImpl implements IPublicacionService {

    @Autowired
    private IPublicacionRepository publicacionRepository;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        // convertir dto a entidad
        Publicacion publicacion = mapearAEntidad(publicacionDTO);
        Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);
        // convertir entidad a dto
        PublicacionDTO publicacionResponse = mapearADTO(nuevaPublicacion);

        return publicacionResponse;
    }

    @Override
    public List<PublicacionDTO> obtenerPublicaciones() {
        List<Publicacion> publicaciones = publicacionRepository.findAll();
        return publicaciones.stream().map(publicacion -> mapearADTO(publicacion)).collect(Collectors.toList());
    }

    @Override
    public PublicacionDTO obtenerPublicacionPorId(long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        return mapearADTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacionPorId(long id, PublicacionDTO publicacionDTO) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());
        Publicacion publicacionActualizada = publicacionRepository.save(publicacion);
        return mapearADTO(publicacionActualizada);
    }

    // convertir entidad a DTO
    private PublicacionDTO mapearADTO(Publicacion publicacion) {
        PublicacionDTO publicacionDTO = new PublicacionDTO();

        publicacionDTO.setId(publicacion.getId());
        publicacionDTO.setTitulo(publicacion.getTitulo());
        publicacionDTO.setDescripcion(publicacion.getDescripcion());
        publicacionDTO.setContenido(publicacion.getContenido());

        return publicacionDTO;
    }

    // convertir DTO a entidad
    private Publicacion mapearAEntidad(PublicacionDTO publicacionDTO) {
        Publicacion publicacion = new Publicacion();

        publicacion.setId(publicacionDTO.getId());
        publicacion.setTitulo(publicacionDTO.getTitulo());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setContenido(publicacionDTO.getContenido());

        return publicacion;
    }
}
