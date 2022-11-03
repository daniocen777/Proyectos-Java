package com.pe.test.pacientes.services;

import com.pe.test.pacientes.entities.Sexo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SexoMyBatisRepository {

    @Results(value = {
            @Result(property = "idSexo", column = "id_sexo"),
            @Result(property = "descripcionSexo", column = "descripcion_sexo"),
            @Result(property = "flEstado", column = "fl_estado")
    })
    @Select("select id_sexo, descripcion_sexo, fl_estado from " + "\"Admision" + "\".tc_sexo")
    public List<Sexo> findAll();
}
