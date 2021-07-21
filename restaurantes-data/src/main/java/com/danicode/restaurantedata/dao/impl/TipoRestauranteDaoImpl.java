package com.danicode.restaurantedata.dao.impl;

// import java.sql.ResultSet;
import com.danicode.restaurantedata.connection.ConnectionFactory;
import java.sql.SQLException;
// import java.util.ArrayList;
import java.util.List;

import com.danicode.restaurantedata.dao.Dao;
import com.danicode.restaurantedata.dao.TipoRestauranteDao;
// import com.danicode.restaurantedata.enums.CodigoEnum;
// import com.danicode.restaurantedata.myexceptions.RestauranteException;
import com.danicode.restauranteentities.entity.TipoRestaurante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TipoRestauranteDaoImpl extends Dao implements TipoRestauranteDao {

//    public TipoRestauranteDaoImpl() {
//        super();
//    }

    /*
	 * static => Bloque que ejecuta sentencias cuando se est� cargando la clase
	 * luego de generarse una instancia de la misma
     */
//    static {
//        try {
//            ConnectionFactory.conectar();
//        } catch (ClassNotFoundException e) {
//            System.out.print("ERROR DE BD ClassNotFoundException => " + e.getMessage());
//            e.printStackTrace();
//        } catch (SQLException e) {
//            System.out.print("ERROR DE BD SQLException => " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
    @Override
    public String guardar(TipoRestaurante tipoRestaurante) {
        sql.delete(0, sql.length()).append("INSERT INTO tipo_restaurante (")
                .append("descripcion,")
                .append("fechaCreacion,")
                .append("estatus")
                .append(") VALUES(?, ?, ?)");
        try ( Connection cn = conn.conectar();  PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            ps.setString(1, tipoRestaurante.getDescripcion().toUpperCase());
            ps.setObject(2, tipoRestaurante.getFechaCreacion());
            ps.setInt(3, tipoRestaurante.isEstatus());

            int ctos = ps.executeUpdate(); // Para insert, delete y update
            message = (ctos != 0) ? null : "0 filas afectadas";
        } catch (SQLException e) {
            message = e.getMessage();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TipoRestauranteDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            // System.out.println(ex);
        }
        return message;
    }

    @Override
    public int actualizar(TipoRestaurante tipoRestaurante) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int eliminar(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TipoRestaurante> consultar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TipoRestaurante consultarPorId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
