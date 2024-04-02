package com.danicode.app.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    @Size(max = 80)
    private String email;

    @NotBlank
    @Size(max = 30)
    private String username;

    @NotBlank
    private String password;

    // Relacion unidirecional con roles
    // Set => NO permite tener elementos DUPLICADOS
    // FetchType.EAGER => Traer TODOS los roles asociados
    // CascadeType.PERSIST => se producirá un guardado en BD tanto de users como de roles
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity.class, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_roles", // tabla intermedia
            joinColumns = @JoinColumn(name = "user_id"), // llave foranea de tabla "users"
            inverseJoinColumns = @JoinColumn(name = "role_id")) // llave foranea de "roles"
    private Set<RoleEntity> roles;

    public UserEntity() {
    }

    public UserEntity(Long id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }
}
