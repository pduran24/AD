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
        String sql = "INSERT INTO videojuegos (nombre, desarrollador, anio_lanzamiento,genero,plataforma) VALUES (?,?,?,?,?)";

        try (Connection connection = ds.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {


            stmt.setString(1, videojuego.getNombre());
            stmt.setString(2, videojuego.getDesarrollador());
            stmt.setInt(3,videojuego.getAnio());
            stmt.setString(4, videojuego.getGenero());
            stmt.setString(5, videojuego.getPlataforma());

            int filasInsertadas = stmt.executeUpdate();

            if (filasInsertadas > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                rs.next();
                videojuego.setId(rs.getInt(1));
                return Optional.of(videojuego);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Videojuego> update(Videojuego videojuego) {
        String sql = "UPDATE videojuegos SET nombre = ?, desarrollador = ?, anio_lanzamiento = ?, genero = ?, plataforma = ?  WHERE id = ?";

        try (Connection connection = ds.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
        ) {
            stmt.setString(1, videojuego.getNombre());
            stmt.setString(2, videojuego.getDesarrollador());
            stmt.setInt(3,videojuego.getAnio());
            stmt.setString(4, videojuego.getGenero());
            stmt.setString(5, videojuego.getPlataforma());
            stmt.setInt(6, videojuego.getId());

            int filasUpdateadas = stmt.executeUpdate();
            if (filasUpdateadas > 0) {
                return Optional.of(videojuego);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Videojuego> delete(Videojuego videojuego) {
        try (Connection connection = ds.getConnection()) {
            String sql = "DELETE FROM videojuegos WHERE id = ?";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, videojuego.getId());

            int filasDeleteadas = stmt.executeUpdate();
            if (filasDeleteadas > 0) {
                return Optional.of(videojuego);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Videojuego> findAll() {
        try (Connection connection = ds.getConnection()){
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM videojuegos");

            ArrayList<Videojuego> listado = new ArrayList<>();

            while (rs.next()) {
                Videojuego videojuego = new Videojuego();
                videojuego.setId(rs.getInt("id"));
                videojuego.setNombre(rs.getString("nombre"));
                videojuego.setDesarrollador(rs.getString("desarrollador"));
                videojuego.setAnio(rs.getInt("anio_lanzamiento"));
                videojuego.setGenero(rs.getString("genero"));
                videojuego.setPlataforma(rs.getString("plataforma"));
                listado.add(videojuego);
            }

            return listado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Videojuego> findById(Integer id) {

        try (Connection connection = ds.getConnection()) {
            String sql = "SELECT * FROM videojuegos WHERE id = ?";


            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Videojuego videojuego = new Videojuego();
                videojuego.setId(rs.getInt("id"));
                videojuego.setNombre(rs.getString("nombre"));
                videojuego.setDesarrollador(rs.getString("desarrollador"));
                videojuego.setAnio(rs.getInt("anio_lanzamiento"));
                videojuego.setGenero(rs.getString("genero"));
                videojuego.setPlataforma(rs.getString("plataforma"));

                return Optional.of(videojuego);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }



}
