package com.danicode.mybatis.mapper;

import org.apache.ibatis.annotations.Param;

import com.danicode.mybatis.model.Employee;
import com.danicode.mybatis.model.response.OkResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> findEmployee();

    OkResponse insertEmployee(@Param("employeeJson") String employeeJson);
}
