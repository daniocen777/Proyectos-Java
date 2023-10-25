package com.danicode.mybatis.model.response;

import com.danicode.mybatis.model.Employee;

import java.util.List;

public class EmployeeListResponse {
    List<Employee> employees;

    public EmployeeListResponse() {
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
