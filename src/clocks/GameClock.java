package clocks;

import actions.Collision;
import game.Snake;

public class GameClock implements Runnable {

    public static boolean running = true;
    public static int velocity = 160;

    public static void prGame() {
        System.out.println(running);
        running = !running;
    }

    public synchronized void run() {

        while (true) {


            //ThreadHandler.syncronizeThreads();
            //System.out.println(running);                    //hours wasted to remove: 0.6

            while (getRunning()) {

                try {
                    if (velocity < 50) {
                        velocity = 50;
                    }
                    Thread.sleep(velocity);
                    Snake.move();
                    Snake.waitToMove = false;

                    Collision.colliedPickUp();
                    Collision.colliedMultiplier();
                    if (Collision.colliedSelf()) {
                        Snake.tails.clear();
                        Snake.head.setX(7);
                        Snake.head.setY(7);
                        Snake.score = 0;

                    }
                    if (Collision.colliedWall()) {
                        Snake.tails.clear();
                        Snake.head.setX(7);
                        Snake.head.setY(7);
                        Snake.score = 0;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public Boolean getRunning() {
        return running;
    }

}
