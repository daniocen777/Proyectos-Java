package com.pe.test.pacientes.services;

import com.pe.test.pacientes.dao.ITipoDocumentoDao;
import com.pe.test.pacientes.entities.TipoDocumentoIdentidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoDocumentoServiceImpl implements ITipoDocumentoService {

    @Autowired
    private ITipoDocumentoDao tipoDocumentoDao;

    @Override
    @Transactional(readOnly = true)
    public List<TipoDocumentoIdentidad> findAll() {
        return this.tipoDocumentoDao.findAll();
    }
}
