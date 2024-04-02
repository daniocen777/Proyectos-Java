package com.danicode.app.requets;

import com.danicode.app.models.RoleEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

public class CreateUserDTO {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;
    private Set<String> roles;

    public CreateUserDTO() {
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
