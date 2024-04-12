package com.danicode.app.controller.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class AuthCreateUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Valid
    private AuthCreateRoleRequest authCreateRoleRequest;

    public AuthCreateUserRequest() {
    }

    public AuthCreateUserRequest(String username, String password, AuthCreateRoleRequest authCreateRoleRequest) {
        this.username = username;
        this.password = password;
        this.authCreateRoleRequest = authCreateRoleRequest;
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

    public AuthCreateRoleRequest getAuthCreateRoleRequest() {
        return authCreateRoleRequest;
    }

    public void setAuthCreateRoleRequest(AuthCreateRoleRequest authCreateRoleRequest) {
        this.authCreateRoleRequest = authCreateRoleRequest;
    }
}
