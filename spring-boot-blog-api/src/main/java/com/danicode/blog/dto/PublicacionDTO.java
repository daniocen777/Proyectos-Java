package com.danicode.blog.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

public class PublicacionDTO {
    private Long id;
    @NotEmpty(message = "Título no debe ser un campo vacío")
    @Size(min = 2, message = "Titulo debe contener al menos 2 caracteres")
    private String titulo;
    @NotEmpty(message = "Descripción no debe ser un campo vacío")
    @Size(min = 10, message = "Descripción debe contener al menos 10 caracteres")
    private String descripcion;
    @NotEmpty(message = "Contenido no debe ser un campo vacío")
    private String contenido;
    private Set<ComentarioDTO> comentarios;

    public PublicacionDTO() {
    }

    public PublicacionDTO(Long id, String titulo, String descripcion, String contenido) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Set<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(Set<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }
}
