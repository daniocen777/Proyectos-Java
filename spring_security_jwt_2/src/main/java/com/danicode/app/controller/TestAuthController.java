package com.danicode.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Configurando las restrincciones desde el controlador (habilitar @EnableMethodSecurity en SecurityConfig)
// @PreAuthorize()
@RestController
@RequestMapping("/method")
// @PreAuthorize("denyAll()") // Por defecto, denegar acceso a todos
public class TestAuthController {

    @GetMapping("/hello")
    // @PreAuthorize("permitAll()")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/hello-secured")
    // @PreAuthorize("hasAuthority('READ')")
    public String helloSecured() {
        return "Hello Secured";
    }

    @GetMapping("/hello-secured2")
    // @PreAuthorize("hasAuthority('CREATE')")
    public String helloSecured2() {
        return "Hello Secured 2";
    }

    @GetMapping("/get")
    // @PreAuthorize("hasAuthority('READ')")
    public String helloGet() {
        return "Hello World - GET";
    }

    @PostMapping("/post")
    // @PreAuthorize("hasAuthority('CREATE') or hasAuthority('READ')")
    public String helloPost() {
        return "Hello World - POST";
    }

    @PutMapping("/put")
    public String helloPut() {
        return "Hello World - PUT";
    }

    @DeleteMapping("/delete")
    public String helloDelete() {
        return "Hello World - DELETE";
    }

    @PatchMapping("/patch")
    // @PreAuthorize("hasAuthority('REFACTOR')")
    public String helloPatch() {
        return "Hello World - PATCH";
    }
}
