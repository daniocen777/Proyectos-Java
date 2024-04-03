package com.danicode.app;

import com.danicode.app.models.ERole;
import com.danicode.app.models.RoleEntity;
import com.danicode.app.models.UserEntity;
import com.danicode.app.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Crear un usuario de prueba para evitar que spring borre la data de la BD al levantar proyecto

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRepository repository;

    // Ejecutar comando cuando aplicaion se levanta
    @Bean
    CommandLineRunner init() {
        return args -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setUsername("danicode");
            userEntity.setPassword(passwordEncoder.encode("1234"));
            // Roles
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setName(ERole.ADMIN);
            userEntity.setRoles(Set.of(roleEntity));
            userEntity.setEmail("danicode@mail.com");

            UserEntity userEntity2 = new UserEntity();
            userEntity2.setUsername("lola");
            userEntity2.setPassword(passwordEncoder.encode("1234"));
            // Roles
            RoleEntity roleEntity2 = new RoleEntity();
            roleEntity2.setName(ERole.USER);
            userEntity2.setRoles(Set.of(roleEntity2));
            userEntity2.setEmail("lolas@mail.com");

            UserEntity userEntity3 = new UserEntity();
            userEntity3.setUsername("adrea");
            userEntity3.setPassword(passwordEncoder.encode("123456"));
            // Roles
            RoleEntity roleEntity3 = new RoleEntity();
            roleEntity3.setName(ERole.INVITED);
            userEntity3.setRoles(Set.of(roleEntity3));
            userEntity3.setEmail("andrea@outlook.com");

            repository.save(userEntity);
            repository.save(userEntity2);
            repository.save(userEntity3);
        };
    }
}