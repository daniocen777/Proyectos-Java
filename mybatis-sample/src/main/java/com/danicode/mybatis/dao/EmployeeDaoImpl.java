package com.danicode.mybatis.dao;

import com.danicode.mybatis.mapper.EmployeeMapper;
import com.danicode.mybatis.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> findEmployee() {
        return employeeMapper.findEmployee();
    }
}
