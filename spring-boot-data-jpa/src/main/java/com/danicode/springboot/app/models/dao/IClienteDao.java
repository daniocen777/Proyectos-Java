package com.danicode.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.danicode.springboot.app.models.entity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

	// Al usar la interface CrudRepository, la implementacion viene por debajo
	/*
	 * Para la paginacion, usar PagingAndSortingRepository => Interface hereda de CrudRepository
	 * y agrega paginacion
	 * */
}
