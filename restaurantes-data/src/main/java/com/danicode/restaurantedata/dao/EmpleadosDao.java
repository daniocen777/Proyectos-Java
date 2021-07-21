package com.danicode.restaurantedata.dao;

import com.danicode.restauranteentities.entity.Empleado;
import java.sql.SQLException;
import java.util.List;

public interface EmpleadosDao {

    int guardar(Empleado empleado) throws SQLException;

    int actualizar(Empleado empleado) throws SQLException;

    int eliminar(int id) throws SQLException;

    List<Empleado> consultar() throws SQLException;

    Empleado consultarPorId(int id) throws SQLException;
}
