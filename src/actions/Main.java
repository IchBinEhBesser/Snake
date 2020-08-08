package actions;

import clocks.GameClock;
import database.DataAccess;
import database.MySQLCon;
import game.Snake;
import gui.Gui;
import gui.Login;

public class Main {

    public static void main(String[] args) {

        MySQLCon con = new MySQLCon();
        Login login = new Login();
        login.create();


    }

    public static void startGame(Boolean b) {

        Snake.bestscore = DataAccess.getHighScore(Login.playerName);

        if (b) {
            Gui g = new Gui();
            GameClock gc = new GameClock();
            g.create();
            gc.start();
        }
    }
}
