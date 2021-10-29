package com.danicode.springboot.backend.apirest.springbootbackendapirest.models.dao;

import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository (hereda de PaginAndSortingRepository => Para usar pagination
public interface IClienteDao extends JpaRepository<Cliente, Long> {
}
