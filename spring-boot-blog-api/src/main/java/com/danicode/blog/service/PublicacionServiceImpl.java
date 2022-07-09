package com.danicode.blog.service;

import com.danicode.blog.dto.PublicacionDTO;
import com.danicode.blog.dto.PublicacionResponse;
import com.danicode.blog.entity.Publicacion;
import com.danicode.blog.exception.ResourceNotFoundException;
import com.danicode.blog.repository.IPublicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PublicacionResponse obtenerPublicaciones(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Publicacion> publicaciones = publicacionRepository.findAll(pageable);
        List<Publicacion> listaDePublicaciones = publicaciones.getContent();
        List<PublicacionDTO> contenido = listaDePublicaciones.stream().map(publicacion -> mapearADTO(publicacion)).collect(Collectors.toList());
        PublicacionResponse publicacionResponse = new PublicacionResponse();
        publicacionResponse.setContenido(contenido);
        publicacionResponse.setPage(publicaciones.getNumber());
        publicacionResponse.setSize(publicaciones.getSize());
        publicacionResponse.setTotalElements(publicaciones.getTotalElements());
        publicacionResponse.setTotalPages(publicaciones.getTotalPages());
        publicacionResponse.setLast(publicaciones.isLast());
        return publicacionResponse;
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

    @Override
    public void eliminarPublicacionPorId(long id) {
        Publicacion publicacion = publicacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publicacion", "id", id));
        publicacionRepository.delete(publicacion);
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
