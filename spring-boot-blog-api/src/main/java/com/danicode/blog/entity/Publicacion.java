package com.danicode.blog.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "publicaciones", uniqueConstraints = {@UniqueConstraint(columnNames = "titulo")})
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String contenido;

    // CascadeType.ALL & orphanRemoval => Cuando se elimine la publicacion, se elimine sus comentarios
    @OneToMany(mappedBy = "publicacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comentario> comentarios = new HashSet<Comentario>();

    public Publicacion() {
    }

    public Publicacion(Long id, String titulo, String descripcion, String contenido) {
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
}
