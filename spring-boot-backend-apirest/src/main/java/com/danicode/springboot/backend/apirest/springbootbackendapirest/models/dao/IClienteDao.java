package com.danicode.springboot.backend.apirest.springbootbackendapirest.models.dao;

import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.entity.Cliente;
import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// JpaRepository (hereda de PaginAndSortingRepository => Para usar pagination
public interface IClienteDao extends JpaRepository<Cliente, Long> {
    // Listado de regiones (solo necesito la lista, no generar el CRUD
    @Query("from Region")
    public List<Region> findAllRegiones();
}
