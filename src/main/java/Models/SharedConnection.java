package Models;

import java.sql.*;

public class SharedConnection {
    private static final String DATABASE_URL = "jdbc:mariadb://localhost:3306/mgttaxi";
    private static String userName;
    private static String password;

    public SharedConnection(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public static Connection createConnection() throws SQLException {
        Connection con = DriverManager.getConnection(DATABASE_URL, userName, password);

        return con;
    }





}