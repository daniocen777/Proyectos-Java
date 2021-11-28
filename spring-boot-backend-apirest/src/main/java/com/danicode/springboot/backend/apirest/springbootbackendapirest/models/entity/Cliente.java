package com.danicode.springboot.backend.apirest.springbootbackendapirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    //private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "no puede ser vacío")
    @Size(min = 4, message = "debe tener al menos 4 caracteres")
    private String nombre;

    @NotEmpty(message = "no puede ser vacío")
    private String apellido;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "no puede ser vacío")
    @Email(message = "no es una dirección de correo bien formada")
    private String email;

    private String foto;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    /* Relacion con Region: Muchos cliente pertenecen a una region |
     FetchType.LAZY => Solo se carga cuando se invoca a region. Ademas
     genera un proxy con varios atributos que no se necesitan "hibernateLazyInitializer"
    // hibernateLazyInitializer */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "hibernateLazyInitializer"})
    private Region region;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
