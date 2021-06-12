package clocks;

import actions.Main;

public class ThreadHandler extends Thread {

    public static void syncronizeThreads() {
        Main.guiThread.notifyAll();
    }
}
