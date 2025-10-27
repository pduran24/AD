import dao.VideojuegoDAO;
import database.DataProvider;
import dao.DAO;
import models.Videojuego;

public class Main {
    public static void main(String[] args) {

        var ds = DataProvider.getDataSource(
                "jdbc:mysql://localhost:3307/ad",
                System.getenv("MYSQL_USER"),
                System.getenv("MYSQL_PASSWORD")
        );


        VideojuegoDAO videojuegoDAO = new VideojuegoDAO(ds);

        videojuegoDAO.findById(3).ifPresentOrElse(System.out::println,()->
                System.out.println("No se encuentra el videojuego"));



        var juego = videojuegoDAO.findById(4);
        if (juego.isPresent()) {
            System.out.println(juego);
            videojuegoDAO.delete(juego.get());
        } else  {
            System.out.println("No existe el videojuego");
        }


        Videojuego juego7 = videojuegoDAO.findById(7).get();
        juego7.setNombre("Editado");

        videojuegoDAO.update(juego7).ifPresentOrElse(
                System.out::println,()-> {
                    System.out.println("No existe el videojuego");
                }
        );

        videojuegoDAO.findById(7).ifPresentOrElse(videojuego -> {
            videojuego.setNombre("Editado2");
            videojuegoDAO.update(videojuego).ifPresent(System.out::println);
        }, () ->  System.out.println("No existe el videojuego")
        );
    }
}
