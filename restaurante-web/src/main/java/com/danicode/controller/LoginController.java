package com.danicode.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

// @ViewScoped => Info almacenada sólo cuando la pantalla esté activa
@ManagedBean
@ViewScoped
public class LoginController {

    private String username;
    private String password;

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

    public void enter() {
        System.out.println(this.username);
        System.out.println(this.password);
    }
}
