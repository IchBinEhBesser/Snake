package actions;

import clocks.GameClock;
import database.DataAccess;
import game.Snake;
import gui.Gui;
import gui.Login;

public class Main {

    //public static MySQLCon mc;

    public static void main(String[] args) {

        Login login = new Login();
        login.create();

    }

    public static void startGame(Boolean b) {
        Snake.bestscore = DataAccess.getPersonalHighscore();
        if (b) {

            DataAccess.loadEssentialData();
            //DataAccess.loadPlayerCount();

            Gui g = new Gui();
            Thread guiThread = new Thread(g);

            GameClock gc = new GameClock();
            Thread gcThread = new Thread(gc);

            guiThread.start();
            gcThread.start();

        }
    }
}
