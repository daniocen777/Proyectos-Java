package com.danicode.mybatis.service;

import com.danicode.mybatis.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findEmployee();

}
