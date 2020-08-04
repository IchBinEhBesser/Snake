package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLCon {

    static Connection con;

    public MySQLCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/snake", "root", "$BV6WMWÜq(<tz!=zHgbvQQ~m");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


