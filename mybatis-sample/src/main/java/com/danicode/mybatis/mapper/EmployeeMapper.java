package com.danicode.mybatis.mapper;

import com.danicode.mybatis.model.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> findEmployee();
}
