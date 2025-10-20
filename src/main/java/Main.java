import dao.VideojuegoDAO;
import database.DataProvider;

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





    }
}
