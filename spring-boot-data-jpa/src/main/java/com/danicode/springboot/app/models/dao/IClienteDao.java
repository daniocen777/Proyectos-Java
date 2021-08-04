package com.danicode.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.danicode.springboot.app.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

	// Al usar la interface CrudRepository, la implementación viene por debajo
}
