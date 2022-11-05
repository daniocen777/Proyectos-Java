package com.pe.test.pacientes.services;

import com.pe.test.pacientes.entities.Ubigeo;

import java.util.List;

public interface IUbigeoService {

    public List<Ubigeo> findAll();

    public List<Ubigeo> findByDepartment(String codigoDepartamento);

}
