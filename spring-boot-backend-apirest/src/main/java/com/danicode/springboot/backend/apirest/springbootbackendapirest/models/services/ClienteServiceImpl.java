package com.danicode.springboot.backend.apirest.springbootbackendapirest.models.services;

import java.util.List;
import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.dao.IClienteDao;
import com.danicode.springboot.backend.apirest.springbootbackendapirest.models.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    private IClienteDao clienteDao; // transactional default

    @Override
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDao.findAll();
    }
}
