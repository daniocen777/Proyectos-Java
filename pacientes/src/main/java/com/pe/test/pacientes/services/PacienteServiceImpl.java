package com.pe.test.pacientes.services;

import com.pe.test.pacientes.dao.IPacienteDao;
import com.pe.test.pacientes.entities.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PacienteServiceImpl implements IPacienteService {

    @Autowired
    private IPacienteDao pacienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Paciente> findAll() {
        return this.pacienteDao.findAll();
    }

    @Override
    public int insert(Paciente paciente) {
        return this.pacienteDao.insert(paciente);
    }
}
