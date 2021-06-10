package database;


import actions.Main;
import gui.Login;
import gui.Register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;


public class DataAccess {

    public static String[] leaderboard = new String[8];
    static Connection con = MySQLCon.con;
    private static int numPlayers = 0;
    private static int personalHighscore = 0;

    public static void loadEssentialData() {
        numPlayers = loadPlayerCount();
        personalHighscore = getPersonalHighscore();
        loadLeaderboard();
        leaderboard = getLeaderboard();
        System.out.println(Arrays.toString(leaderboard));

    }


    public static Boolean confirmPlayer(String pNa, String pPw) {

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select playerName, playerPassword from player");
            while (rs.next())
                if (rs.getString(1).equals(pNa) && rs.getString(2).equals(pPw)) {
                    Login.playerName = pNa;
                    Login.playerPassword = pPw;
                    Login.jFrame.setVisible(false);
                    Main.startGame(true);

                }
            Login.logInfo.setVisible(true);
            return false;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static Boolean registradePlayer(String pNa, String pPw) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT playerName from player where playerName = 'Maxi';");
            rs.next();
            if (rs.getString(0).equals(pNa)) {
                Register.regInfo.setVisible(true);
            } else {
                stmt.executeUpdate("INSERT INTO player (playerName,playerPassword) VALUES ('" + pNa + "','" + pPw + "');");
                Login.regInfo.setVisible(true);
                Register.jFrame.dispose();
            }
            return false;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }


    public static int loadPlayerCount() {

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from player");
            while (rs.next())
                numPlayers = rs.getInt(1);
            return numPlayers;

        } catch (Exception e) {
            System.out.println(e);
        }
        return numPlayers = 0;

    }

    public static int getPlayerCount() {
        return numPlayers;
    }

    public static int getPersonalHighscore() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select max(highscore) from player where playerName = '" + Login.playerName + "'");
            while (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public static void setPersonalHighscore(int highScore) {
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

    public static void loadLeaderboard() {

        String[] scoreArray = new String[numPlayers];

        for (int i = 0; i < numPlayers; i++) {
            scoreArray[i] = loadSingleHighscore(i);

        }
        leaderboard = scoreArray;

    }

    public static String loadSingleHighscore(int num) {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT playerName, highscore FROM player ORDER BY highscore desc LIMIT " + num + "," + (num + 1) + ";");
            while (rs.next()) {
                return (rs.getString(1) + " " + rs.getInt(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "Error in getLeaderScore()";
    }

    public static String[] getLeaderboard() {
        return leaderboard;
    }


}
