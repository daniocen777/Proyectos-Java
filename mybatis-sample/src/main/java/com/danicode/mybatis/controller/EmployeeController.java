package com.danicode.mybatis.controller;

import com.danicode.mybatis.model.Employee;
import com.danicode.mybatis.model.request.InsertEmployeeRequest;
import com.danicode.mybatis.model.response.EmployeeListResponse;
import com.danicode.mybatis.model.response.OkResponse;
import com.danicode.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
        return new ResponseEntity<>(employeeListResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/employees")
    public ResponseEntity<OkResponse> insertEmployee(@RequestBody InsertEmployeeRequest insertEmployeeRequest) {
        OkResponse okResponse = new OkResponse();
        System.out.println("Request \t => " + insertEmployeeRequest);
        try {
            okResponse = employeeService.insertEmployee(insertEmployeeRequest);
            return new ResponseEntity<>(okResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println("Error al insertar empleado: \n" + e.getMessage());
            okResponse.setOk(false);
            okResponse.setMessage("Error: \t" + e.getMessage());
            okResponse.setResult("Error");
            return new ResponseEntity<>(okResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
