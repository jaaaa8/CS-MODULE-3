package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static final String jdbcURL = "jdbc:mysql://localhost:3306/case_std_bookstore_db;";
    private static final String jdbcUsername = "root";
    private static final String jdbcPassword = "codegym";

    public static Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }catch(SQLException | ClassNotFoundException e){
            System.err.println(e.getMessage());
        }
        return conn;
    }
}
