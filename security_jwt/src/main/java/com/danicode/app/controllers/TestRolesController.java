package com.danicode.app.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRolesController {

    // Habilitar @EnableGlobalMethodSecurity(prePostEnabled = true) en SecurityConfig

    @GetMapping("/access-admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String accessAdmin() {
        return "Role de ADMIN";
    }

    @GetMapping("/access-user")
    @PreAuthorize("hasRole('USER')")
    public String accessUser() {
        return "Role de USER";
    }

    @GetMapping("/access-invited")
    @PreAuthorize("hasRole('INVITED')")
    public String accessInvited() {
        return "Role de INVITED";
    }
}
