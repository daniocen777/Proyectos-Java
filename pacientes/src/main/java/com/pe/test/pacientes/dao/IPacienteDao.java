package com.pe.test.pacientes.dao;

import com.pe.test.pacientes.entities.Paciente;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

@Mapper
public interface IPacienteDao {

    @Results(value = {
            @Result(property = "idPaciente", column = "id_paciente"),
            @Result(property = "idTipoDocide", column = "id_tipo_docide"),
            @Result(property = "noDocide", column = "no_docide"),
            @Result(property = "noApepat", column = "no_apepat"),
            @Result(property = "noApemat", column = "no_apemat"),
            @Result(property = "noNombres", column = "no_nombres"),
            @Result(property = "idSexo", column = "id_sexo"),
            @Result(property = "feNacimiento", column = "fe_nacimiento"),
            @Result(property = "noLugarNacimiento", column = "no_lugar_nacimiento"),
            @Result(property = "noDireccion", column = "no_direccion"),
            @Result(property = "coUbigeo", column = "co_ubigeo")
    })
    @Select("SELECT id_paciente, id_tipo_docide, no_docide, no_apepat, no_apemat, no_nombres, id_sexo, fe_nacimiento, no_lugar_nacimiento, no_direccion, co_ubigeo\n" +
            "FROM \"Admision\".tb_paciente;\n")
    public List<Paciente> findAll();

    @Insert("INSERT INTO \"Admision\".tb_paciente\n" +
            "(id_tipo_docide, no_docide, no_apepat, no_apemat, no_nombres, id_sexo, fe_nacimiento, no_lugar_nacimiento, no_direccion, co_ubigeo)\n" +
            "VALUES(#{idTipoDocide}, #{noDocide}, #{noApepat}, #{noApemat}, #{noNombres}, #{idSexo}, #{feNacimiento}, #{noLugarNacimiento}, #{noDireccion}, #{coUbigeo})")
    public int insert(Paciente paciente);
}
