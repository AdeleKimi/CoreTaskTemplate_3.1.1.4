package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util  {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb?autoReconnect=true&useSSL=false";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "Vbifyz1995";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,LOGIN,PASSWORD);
        }catch (SQLException e) {
            e.printStackTrace();
        }


        return connection;
    }
    // реализуйте настройку соеденения с БД
}
