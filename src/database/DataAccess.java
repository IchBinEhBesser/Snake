package database;


import actions.Main;
import gui.Login;

import java.sql.*;

public class DataAccess {

    static Connection con = MySQLCon.con;


    public static Boolean checkPlayer(String pNa, String pPw) {

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select playerName, playerPassword from player");
            while (rs.next())
                if (rs.getString(1).equals(pNa) && rs.getString(2).equals(pPw)) {
                    Login.playerName = pNa;
                    Login.playerPassword = pPw;
                    Main.startGame(true);
                }
            Main.startGame(false);
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static int getHighScore() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(highscore) from player");
            while (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public static void setHighScore(int highScore) {
        try {
            String query = "update player set highscore = ? where playerName = ?";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setInt(1, highScore);
            preparedStmt.setString(2, Login.playerName);

            preparedStmt.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public static String getLeaderScore(int num) {

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT playerName, highscore FROM player ORDER BY !highscore LIMIT " + num + "," + (num + 1) + ";");
            while (rs.next()) {
                return (rs.getString(1) + " " + rs.getInt(2));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return "Error in getLeaderScore()";
    }
}
