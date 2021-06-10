package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLCon {

    public static Connection con;

    public static String serverIp;

    public MySQLCon(String sIp) {

        serverIp = sIp;

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + MySQLCon.serverIp + ":3306/snake", "SnakeUser2", "test");

        } catch (SQLException e) {
            con = null;
            e.printStackTrace();
        }
    }
}


