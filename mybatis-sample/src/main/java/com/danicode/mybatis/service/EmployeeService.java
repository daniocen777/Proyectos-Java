package com.danicode.mybatis.service;

import com.danicode.mybatis.model.Employee;
import com.danicode.mybatis.model.request.InsertEmployeeRequest;
import com.danicode.mybatis.model.response.OkResponse;

import java.util.List;

public interface EmployeeService {
    List<Employee> findEmployee();

    OkResponse insertEmployee(InsertEmployeeRequest insertEmployeeRequest);

}
