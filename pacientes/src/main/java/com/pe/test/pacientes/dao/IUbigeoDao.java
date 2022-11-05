package com.pe.test.pacientes.dao;
import com.pe.test.pacientes.entities.Ubigeo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IUbigeoDao {

    @Results(value = {
            @Result(property = "codigoUbigeo", column = "codigo_ubigeo"),
            @Result(property = "descripcionDepartamento", column = "descripcion_departamento"),
            @Result(property = "descripcionProvincia", column = "descripcion_provincia"),
            @Result(property = "descripcionDistrito", column = "descripcion_distrito"),
            @Result(property = "flEstado", column = "fl_estado"),
            @Result(property = "codigoDepartamento", column = "codigo_departamento"),
            @Result(property = "codigoProvincia", column = "codigo_provincia"),
            @Result(property = "codigoDistrito", column = "codigo_distrito")
    })
    @Select("SELECT codigo_ubigeo, descripcion_departamento, descripcion_provincia, descripcion_distrito, fl_estado, codigo_departamento, codigo_provincia, codigo_distrito\n" +
            "    FROM \"Admision\".tc_ubigeo")
    public List<Ubigeo> findAll();


    @Results(value = {
            @Result(property = "codigoUbigeo", column = "codigo_ubigeo"),
            @Result(property = "descripcionDepartamento", column = "descripcion_departamento"),
            @Result(property = "descripcionProvincia", column = "descripcion_provincia"),
            @Result(property = "descripcionDistrito", column = "descripcion_distrito"),
            @Result(property = "flEstado", column = "fl_estado"),
            @Result(property = "codigoDepartamento", column = "codigo_departamento"),
            @Result(property = "codigoProvincia", column = "codigo_provincia"),
            @Result(property = "codigoDistrito", column = "codigo_distrito")
    })
    @Select("SELECT descripcion_departamento, descripcion_provincia, descripcion_distrito, fl_estado, codigo_ubigeo, codigo_departamento, codigo_provincia, codigo_distrito\n" +
            "FROM \"Admision\".tc_ubigeo where codigo_departamento = #{codigoDepartamento};")
    public List<Ubigeo> findByDepartment(String codigoDepartamento);
}
