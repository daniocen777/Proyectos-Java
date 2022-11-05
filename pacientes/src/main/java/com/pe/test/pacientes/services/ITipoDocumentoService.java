package com.pe.test.pacientes.services;

import com.pe.test.pacientes.entities.TipoDocumentoIdentidad;

import java.util.List;

public interface ITipoDocumentoService {

    public List<TipoDocumentoIdentidad> findAll();

}
