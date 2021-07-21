package com.danicode.restaurantedata.dao.impl;

import com.danicode.restaurantedata.connection.ConnectionFactory;
import com.danicode.restaurantedata.dao.EmpleadosDao;
import com.danicode.restauranteentities.entity.Empleado;
import com.danicode.restauranteentities.entity.Rol;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmpleadoDaoImpl implements EmpleadosDao {
    
    @Override
    public int guardar(Empleado empleado) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int actualizar(Empleado empleado) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public int eliminar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public List<Empleado> consultar() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public Empleado consultarPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public Empleado consultarPorUsuarioYPassword(String usuario, String password,
            boolean esSuperAdminGeneral) throws SQLException {
//        Empleado empleado = null;
//        String sql = "";
//        if (esSuperAdminGeneral) {
//            sql = "SELECT e.*, r.nombre AS nombreRol FROM empleado e, "
//                    + "rol r WHERE e.idRol = r.idRol "
//                    + "AND (e.usuario = '" + usuario + "' OR e.email = '" + usuario + "') "
//                    + "AND e.password = '" + password + "' AND e.idSucursal IS NULL";
//        } else {
//            // Administrador sucursal o empleado
//        }
//        
//        ResultSet rs = ConnectionFactory.ejecutarSqlSelect(sql);
//        if (rs != null) {
//            if (rs.next()) {
//                empleado = new Empleado();
//                empleado.setIdEmpleado(rs.getInt("idEmpleado"));
//                empleado.setNombre(rs.getString("nombre"));
//                empleado.setPrimerApellido(rs.getString("primerApellido"));
//                empleado.setSegundoApellido(rs.getString("segundoApellido"));
//                empleado.setUsuario(rs.getString("usuario"));
//                empleado.setPassword(rs.getString("password"));
//                empleado.setEmail(rs.getString("email"));
//                empleado.setEstatus(rs.getInt("estatus"));
//                empleado.setSuperadmin(rs.getInt("superadmin"));
//                empleado.setSuperadmingeneral(rs.getInt("superadmingeneral"));
//                
//                Rol rol = new Rol();
//                rol.setIdRol(rs.getInt("idRol"));
//                rol.setNombre(rs.getString("nombreRol"));
//                empleado.setRol(rol);
//                // No es superAdmiGeneral
//                if (empleado.getSuperadmingeneral() == 0) {
//                    
//                }
//            }
//        }
        return null;
    }
    
}
