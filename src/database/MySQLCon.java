package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLCon {

    static Connection con;

    public MySQLCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://79.200.38.45:3306/snake", "SnakeUser", "snake");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


