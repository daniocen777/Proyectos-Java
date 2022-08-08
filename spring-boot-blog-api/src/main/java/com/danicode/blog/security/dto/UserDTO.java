package com.danicode.blog.security.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserDTO {

    @NotBlank
    private String username;

    @Email
    private String email;

    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(String username, String email, String password, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
