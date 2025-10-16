package dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class VidejuegoDAO implements DAO<Videojuego> {

    private DataSource ds;
    public VidejuegoDAO(DataSource dataSource) {
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
        return Optional.empty();
    }


}
