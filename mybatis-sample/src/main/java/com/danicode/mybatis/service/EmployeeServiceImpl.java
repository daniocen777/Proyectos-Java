package com.danicode.mybatis.service;

import com.danicode.mybatis.dao.EmployeeDao;
import com.danicode.mybatis.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> findEmployee() {
        return employeeDao.findEmployee();
    }
}
