package com.pe.test.pacientes.services;

import com.pe.test.pacientes.entities.Paciente;

import java.util.List;

public interface IPacienteService {

    public List<Paciente> findAll();

    public int insert(Paciente paciente);

}
