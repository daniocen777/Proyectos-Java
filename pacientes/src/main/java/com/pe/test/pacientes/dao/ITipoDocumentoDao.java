package com.pe.test.pacientes.dao;

import com.pe.test.pacientes.entities.TipoDocumentoIdentidad;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ITipoDocumentoDao {

    @Results(value = {
            @Result(property = "idTipoDocumentoIdentidad", column = "id_tipo_documento_identidad"),
            @Result(property = "descripcionTipoDocumentoIdentidad", column = "descripcion_tipo_documento_identidad"),
            @Result(property = "codigoIeds", column = "codigo_ieds"),
            @Result(property = "flEstado", column = "fl_estado")
    })
    @Select("SELECT id_tipo_documento_identidad, descripcion_tipo_documento_identidad, codigo_ieds, fl_estado\n" +
            "FROM \"Admision\".tc_tipo_documento_identidad")
    public List<TipoDocumentoIdentidad> findAll();
}
