package com.danicode.springboot.backend.apirest.springbootbackendapirest.models.services;

import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.dao.IUsuarioDao;
import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UserDetailsService {
    // Inyectando dao
    @Autowired
    private IUsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Log
        Logger logger = LoggerFactory.getLogger(UsuarioService.class);
        // Usuario por username
        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            logger.error("Error en el login: No existe usuario '" + username + "' en el sistme");
            throw new UsernameNotFoundException("Error en el login: No existe usuario '" + username + "' en el sistme");
        }
        // authorities => roles
        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombre()))
                .peek(authority -> logger.info("Role: " + authority.getAuthority()))
                .collect(Collectors.toList());

        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getEnabled(), true, true, true, authorities);
    }
}
