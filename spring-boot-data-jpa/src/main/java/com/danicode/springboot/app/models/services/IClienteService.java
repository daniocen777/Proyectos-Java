package com.danicode.springboot.app.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.danicode.springboot.app.models.entity.Cliente;

public interface IClienteService {
	public List<Cliente> findAll();

	// Importar de org.springframework.data.domain.Pageable;
	public Page<Cliente> findAll(Pageable pageable);

	public void save(Cliente cliente);

	public Cliente findOne(Long id);

	public void delete(Long id);
}
