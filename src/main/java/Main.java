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

        System.out.println(videojuegoDAO.findById(1));





    }
}
