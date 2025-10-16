package database;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;


public class DataProvider {

    private static DataSource dataSource;

    private DataProvider() {}

    public static DataSource getDataSource( String URL, String USERNAME, String PASSWORD ) {
        if (dataSource == null) {
            var ds= new MysqlDataSource();
            ds.setURL(URL);
            ds.setUser(USERNAME);
            ds.setPassword(PASSWORD);

            dataSource = ds;
        }
        return dataSource;
    }


    public static DataSource getDataSource() {
        return dataSource;
    }


}
