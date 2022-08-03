package com.danicode.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ComentarioDTO {

    private long id;
    @NotEmpty(message = "Nombre no debe ser un campo vacío")
    private String nombre;
    @NotEmpty(message = "Correo no debe ser un campo vacío")
    @Email(message = "Correo no tiene el formato correcto")
    private String email;
    @NotEmpty(message = "Comentario no debe ser un campo vacío")
    @Size(min = 10, message = "Comentario debe tener al menos 10 caracteres")
    private String cuerpo;

    public ComentarioDTO() {
    }

    public ComentarioDTO(long id, String nombre, String email, String cuerpo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.cuerpo = cuerpo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
}
