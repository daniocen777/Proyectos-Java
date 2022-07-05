package com.danicode.springboot.backend.apirest.springbootbackendapirest.models.dao;

import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

    public Usuario findByUsername(String username);
}
