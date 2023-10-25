package com.danicode.mybatis.dao;

import com.danicode.mybatis.model.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> findEmployee();
}
