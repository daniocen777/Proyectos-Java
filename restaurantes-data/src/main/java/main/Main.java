package main;

import com.danicode.restaurantedata.dao.TipoRestauranteDao;
import com.danicode.restaurantedata.dao.impl.TipoRestauranteDaoImpl;
import com.danicode.restauranteentities.entity.TipoRestaurante;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        TipoRestauranteDao dao = new TipoRestauranteDaoImpl();
        TipoRestaurante tipoRestaurante = new TipoRestaurante();

        tipoRestaurante.setDescripcion("Nuevo Restaurante");
        tipoRestaurante.setFechaCreacion(LocalDateTime.now());
        tipoRestaurante.setEstatus(1);

        System.out.println(dao.guardar(tipoRestaurante));

//        ConnectionFactory co = new ConnectionFactory();
//        try {
//            if (co.conectar() != null) {
//
//                System.out.println("OK");
//            } else {
//                System.out.println("ERROR");
//            }
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
