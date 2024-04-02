package com.danicode.app.controllers;

import com.danicode.app.models.ERole;
import com.danicode.app.models.RoleEntity;
import com.danicode.app.models.UserEntity;
import com.danicode.app.repositories.IUserRepository;
import com.danicode.app.requets.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class MainController {

    @Autowired
    private IUserRepository repository;

    @GetMapping("/hello")
    public String saidHello() {
        return "Hello, NOT security";
    }

    @GetMapping("/secure-hello")
    public String saidSecurityHello() {
        return "Hello, security";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        Set<RoleEntity> roles = createUserDTO.getRoles()
                .stream()
                .map(role -> {
                    RoleEntity roleEntity = new RoleEntity();
                    roleEntity.setName(ERole.valueOf(role));
                    return roleEntity;
                }).collect(Collectors.toSet());

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(createUserDTO.getUsername());
        userEntity.setPassword(createUserDTO.getPassword());
        userEntity.setEmail(createUserDTO.getEmail());
        userEntity.setRoles(roles);

        repository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@RequestParam String id) {
        repository.deleteById(Long.parseLong(id));
        return "Usuario eliminado correctamente con ID ".concat(id);
    }
}
