package dao;

import models.Videojuego;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class VideojuegoDAO implements DAO<Videojuego> {

    private DataSource ds;
    public VideojuegoDAO(DataSource dataSource) {
        this.ds = dataSource;
    }


    @Override
    public Optional<Videojuego> save(Videojuego videojuego) {
        return Optional.empty();
    }

    @Override
    public Optional<Videojuego> update(Videojuego videojuego) {
        return Optional.empty();
    }

    @Override
    public Optional<Videojuego> delete(Videojuego videojuego) {
        return Optional.empty();
    }

    @Override
    public List<Videojuego> findAll() {
        return null;
    }

    @Override
    public Optional<Videojuego> findById(Integer id) {

        try (Connection connection = ds.getConnection()) {
            Videojuego videojuego = new Videojuego();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM videojuegos WHERE id = " + id);

            if (rs.next()) {

                videojuego.setId(rs.getInt("id"));
                videojuego.setNombre(rs.getString("nombre"));
                videojuego.setDesarrollador(rs.getString("desarrollador"));
                videojuego.setAnio(rs.getInt("anio_lanzamiento"));
                videojuego.setGenero(rs.getString("genero"));
                videojuego.setPlataforma(rs.getString("plataforma"));
            }

            return Optional.of(videojuego);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
