package com.danicode.mybatis.model.request;

import com.danicode.mybatis.model.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InsertEmployeeRequest {
    private Employee employee;
    private String employeeJson;

    public InsertEmployeeRequest() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(employee);
            String a = json.replaceAll(Pattern.quote("\\"), Matcher.quoteReplacement(""));
            String b = a.replaceAll(Pattern.quote("\"["), Matcher.quoteReplacement("["));
            String c = b.replaceAll(Pattern.quote("]\""), Matcher.quoteReplacement("]"));
            setEmployeeJson(c);

        } catch (Exception e) {
            System.out.println("Error al convertir a JSON => " + e.getMessage());
        }
    }

    public String getEmployeeJson() {
        return employeeJson;
    }

    public void setEmployeeJson(String employeeJson) {
        this.employeeJson = employeeJson;
    }

    @Override
    public String toString() {
        return "InsertEmployeeRequest{" +
                "employee=" + employee +
                ", employeeJson='" + employeeJson + '\'' +
                '}';
    }
}
