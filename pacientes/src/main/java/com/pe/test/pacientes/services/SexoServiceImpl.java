package com.pe.test.pacientes.services;

import com.pe.test.pacientes.dao.ISexoDao;
import com.pe.test.pacientes.entities.Sexo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SexoServiceImpl implements ISexoService {

    @Autowired
    private ISexoDao sexoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Sexo> findAll() {
        return this.sexoDao.findAll();
    }
}
