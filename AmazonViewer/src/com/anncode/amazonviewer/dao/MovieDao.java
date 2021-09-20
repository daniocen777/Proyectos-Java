package com.anncode.amazonviewer.dao;

impexecuteUpdateort com.anncode.amazonviewer.db.IDBConnection;
import com.anncode.amazonviewer.model.Movie;

import static com.anncode.amazonviewer.db.DataBase.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <h1>Movie</h1>
 * <p>
 * Interface que contiene métodos de tipo default (métodos
 * que aceptan implementación - desde java 8) que se usarán
 * para el CRUD de la tabla movie
 * </p>
 */
public interface MovieDao extends IDBConnection {
    // 8:00
    default Movie setMovieViewer(Movie movie) {
        StringBuilder sql = new StringBuilder();
        String message = "";
        sql.delete(0, sql.length())
                .append("INSERT INTO " + TVIEWED)
                .append("(" + TVIEWED_ID_MATERIAL + "," + TVIEWED_ID_ELEMENT + "," + TVIEWED_ID_USER + ")")
                .append(" VALUES(?, ?, ?)");
        try (Connection connection = connectToDB();
             PreparedStatement ps = connection.prepareStatement(sql.toString())) {
            ps.setInt(1, TMATERIAL_IDS[0]);
            ps.setInt(2, movie.getId());
            ps.setInt(3, TUSER_ID_USUARIO);

            int ctos = ps.executeUpdate(); // Para insert, delete y update
            message = (ctos != 0) ? "OK" : "Error ==> setMovieViewer | executeUpdate()";
            System.out.println(message);
        } catch (SQLException e) {
            System.out.println("Error ==>" + e.getMessage());
        }
        return movie;
    }

    default List<Movie> read() {
        List<Movie> movies = null;
        StringBuilder sql = new StringBuilder();
        sql.delete(0, sql.length())
                .append("SELECT * ")
                .append("FROM " + TMOVIE);
        try (Connection connection = connectToDB();
             PreparedStatement ps = connection.prepareStatement(sql.toString());
             ResultSet rs = ps.executeQuery()) {
            movies = new LinkedList<>();
            while (rs.next()) {
                Movie movie = new Movie(rs.getString(TMOVIE_TITLE),
                        rs.getString(TMOVIE_GENRE), rs.getString(TMOVIE_CREATOR),
                        rs.getInt(TMOVIE_DURATION), rs.getShort(TMOVIE_YEAR));
                movie.setViewed(getMovieViewed(connection, ps, rs.getInt(TMOVIE_ID)));
                movies.add(movie);
            }
        } catch (SQLException e) {
            System.out.println("Error ==>" + e.getMessage());
        }
        return movies;
    }

    private boolean getMovieViewed(Connection connection, PreparedStatement ps, int idMovie) {
        // TMATERIAL_IDS[0] => movie
        boolean viewed = false;
        StringBuilder sql = new StringBuilder();
        sql.delete(0, sql.length())
                .append("SELECT * ")
                .append("FROM " + TVIEWED)
                .append(" WHERE " + TVIEWED_ID_MATERIAL + "= ?")
                .append(" AND " + TVIEWED_ID_ELEMENT + "= ?")
                .append(" AND " + TVIEWED_ID_USER + "= ?");
        try {
            ps = connection.prepareStatement(sql.toString());
            ps.setInt(1, TMATERIAL_IDS[0]);
            ps.setInt(2, idMovie);
            ps.setInt(3, TUSER_ID_USUARIO);
            try (ResultSet rs = ps.executeQuery()) {
                viewed = rs.next();
            } catch (SQLException e) {
                System.out.println("Error ResultSet ==> " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Error prepareStatement ==> " + e.getMessage());
        }
        return viewed;
    }
}
