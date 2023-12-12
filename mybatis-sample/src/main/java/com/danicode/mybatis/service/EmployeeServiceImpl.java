package com.danicode.mybatis.service;

import com.danicode.mybatis.dao.EmployeeDao;
import com.danicode.mybatis.model.Employee;
import com.danicode.mybatis.model.request.InsertEmployeeRequest;
import com.danicode.mybatis.model.response.OkResponse;
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

    @Override
    public OkResponse insertEmployee(InsertEmployeeRequest insertEmployeeRequest) {
        Employee employee = insertEmployeeRequest.getEmployee();
        insertEmployeeRequest.setEmployee(employee);

        System.out.println("Empleado JSON: \n" + insertEmployeeRequest);

        OkResponse response = employeeDao.insertEmployee(insertEmployeeRequest);
        return response;
    }
}
