package Models;

import java.sql.*;

public class SharedConnection {
    private static final String DATABASE_URL = "jdbc:mariadb://localhost:3306/crm";
    private static String userName = "root";
    private static String password = "reda";

    public SharedConnection(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public static Connection createConnection() throws SQLException {
        Connection con = DriverManager.getConnection(DATABASE_URL, userName, password);
        return con;
    }





}