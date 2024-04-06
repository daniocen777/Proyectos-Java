package com.danicode.app;

import com.danicode.app.persitence.entity.PermissionEntity;
import com.danicode.app.persitence.entity.RoleEntity;
import com.danicode.app.persitence.entity.UserEntity;
import com.danicode.app.persitence.enums.RoleEnum;
import com.danicode.app.persitence.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    private IUserRepository repository;

    @Bean
    CommandLineRunner init() {
        System.out.println("INICIO");
        return args -> {
            // Permisos
            PermissionEntity createPermission = new PermissionEntity();
            createPermission.setName("CREATE");

            PermissionEntity readPermission = new PermissionEntity();
            readPermission.setName("READ");

            PermissionEntity updatePermission = new PermissionEntity();
            updatePermission.setName("UPDATE");

            PermissionEntity deletePermission = new PermissionEntity();
            deletePermission.setName("DELETE");

            PermissionEntity refactorPermission = new PermissionEntity();
            refactorPermission.setName("REFACTOR");
            System.out.println("TERMINO PERMISSIONS");
            // Roles
            RoleEntity roleAdmin = new RoleEntity();
            roleAdmin.setRole(RoleEnum.ADMIN);
            roleAdmin.setPermissions(Set.of(createPermission, readPermission, updatePermission, deletePermission));

            RoleEntity roleUser = new RoleEntity();
            roleUser.setRole(RoleEnum.USER);
            roleUser.setPermissions(Set.of(createPermission, readPermission));

            RoleEntity roleInvited = new RoleEntity();
            roleInvited.setRole(RoleEnum.INVITED);
            roleInvited.setPermissions(Set.of(readPermission));

            RoleEntity roleDeveloper = new RoleEntity();
            roleDeveloper.setRole(RoleEnum.DEVELOPER);
            roleDeveloper.setPermissions(Set.of(createPermission,
                    readPermission,
                    updatePermission,
                    deletePermission,
                    refactorPermission));
            // Usuarios
            UserEntity userDaniel = new UserEntity();
            userDaniel.setUsername("daniel");
            userDaniel.setPassword("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6");
            userDaniel.setEnabled(true);
            userDaniel.setAccountNoExpired(true);
            userDaniel.setAccountNoLocked(true);
            userDaniel.setCredentialNoExpired(true);
            userDaniel.setRoles(Set.of(roleAdmin));

            UserEntity userLola = new UserEntity();
            userLola.setUsername("lola");
            userLola.setPassword("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6");
            userLola.setEnabled(true);
            userLola.setAccountNoExpired(true);
            userLola.setAccountNoLocked(true);
            userLola.setCredentialNoExpired(true);
            userLola.setRoles(Set.of(roleUser));

            UserEntity userAndrea = new UserEntity();
            userAndrea.setUsername("andrea");
            userAndrea.setPassword("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6");
            userAndrea.setEnabled(true);
            userAndrea.setAccountNoExpired(true);
            userAndrea.setAccountNoLocked(true);
            userAndrea.setCredentialNoExpired(true);
            userAndrea.setRoles(Set.of(roleInvited));

            UserEntity userDeveloper = new UserEntity();
            userDeveloper.setUsername("developer");
            userDeveloper.setPassword("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6");
            userDeveloper.setEnabled(true);
            userDeveloper.setAccountNoExpired(true);
            userDeveloper.setAccountNoLocked(true);
            userDeveloper.setCredentialNoExpired(true);
            userDeveloper.setRoles(Set.of(roleDeveloper));

            // Cuando se gurde el usuario, se guardaran los roles y sus permisos (CASCADE.ALL)
            repository.saveAll(List.of(userDaniel, userLola, userAndrea, userDeveloper));
        };
    }
}