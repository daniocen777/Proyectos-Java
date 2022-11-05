package com.pe.test.pacientes.services;

import com.pe.test.pacientes.dao.IUbigeoDao;
import com.pe.test.pacientes.entities.Ubigeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UbigeoServiceImpl implements IUbigeoService {

    @Autowired
    private IUbigeoDao ubigeoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Ubigeo> findAll() {
        return this.ubigeoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Ubigeo> findByDepartment(String codigoDepartamento) {
        return this.ubigeoDao.findByDepartment(codigoDepartamento);
    }
}
