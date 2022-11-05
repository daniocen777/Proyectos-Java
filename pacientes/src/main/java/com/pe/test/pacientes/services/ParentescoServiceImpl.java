package com.pe.test.pacientes.services;

import com.pe.test.pacientes.dao.IParentescoDao;
import com.pe.test.pacientes.entities.Parentesco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentescoServiceImpl implements IParentescoService {

    @Autowired
    private IParentescoDao parentescoDao;

    @Override
    public List<Parentesco> findAll() {
        return this.parentescoDao.findAll();
    }
}
