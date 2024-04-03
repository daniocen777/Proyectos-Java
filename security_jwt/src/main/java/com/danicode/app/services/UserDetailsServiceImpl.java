package com.danicode.app.services;

import com.danicode.app.models.UserEntity;
import com.danicode.app.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository repository;

    // Spring security consulta por debajo (de donde salen los usuarios)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Recuperar el usuario de la BD
        UserEntity userEntity = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe"));
        // Roles son GrantedAuthority
        Collection<? extends GrantedAuthority> authorities = userEntity.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getName().name())))
                .collect(Collectors.toSet());
        // Setearlo a spring security
        // User(username, password, usuario_activo, cuenta_no_expira, credenciales_no_expiran, cuenta_no_se_bloquea)
        return new User(userEntity.getUsername(),
                userEntity.getPassword(),
                true,
                true,
                true, true,
                authorities);
    }
}
