package com.danicode.mybatis.controller;

import com.danicode.mybatis.model.Employee;
import com.danicode.mybatis.model.response.EmployeeListResponse;
import com.danicode.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/employees")
    public ResponseEntity<EmployeeListResponse> getEmployees() {
        // System.out.println("*********************************************");
        List<Employee> employees = employeeService.findEmployee();
        EmployeeListResponse employeeListResponse = new EmployeeListResponse();
        employeeListResponse.setEmployees(employees);
        // employees.stream().map(Employee::getName).forEach(System.out::println);
        //  model.addAttribute("employeeList", employees);
        return new ResponseEntity<>(employeeListResponse, HttpStatus.OK);
    }
}
