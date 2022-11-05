package com.pe.test.pacientes.dao;

import com.pe.test.pacientes.entities.Parentesco;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IParentescoDao {

    @Results(value = {
            @Result(property = "idParentesco", column = "id_parentesco"),
            @Result(property = "noParentesco", column = "no_parentesco"),
            @Result(property = "ilActivo", column = "il_activo")
    })
    @Select("SELECT id_parentesco, no_parentesco, il_activo\n" +
            "FROM \"Admision\".tc_parentesco;\n")
    public List<Parentesco> findAll();
}
