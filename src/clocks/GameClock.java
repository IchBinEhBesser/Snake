package clocks;

import actions.Collision;
import game.Snake;

public class GameClock implements Runnable {

    public static boolean running = true;
    public static int velocity = 160;

    public static void prGame() {
        running = !running;
    }

    public void run() {
        while (true) {

            while (running) {

                try {
                    if (velocity < 20) {
                        velocity = 20;
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
}
