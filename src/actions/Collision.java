package actions;

import clocks.GameClock;
import database.DataAccess;
import game.Snake;

public class Collision {

    public static boolean colliedSelf() {


        DataAccess.setPersonalHighscore(Snake.bestscore);
        for (int i = 0; i < Snake.tails.size(); i++) {
            if (Snake.head.getX() == Snake.tails.get(i).getX() && Snake.head.getY() == Snake.tails.get(i).getY() && !Snake.tails.get(i).isWait()) {
                GameClock.velocity = 200;
                DataAccess.loadLeaderboard();

                return true;
            }
        }
        return false;
    }

    public static boolean colliedWall() {

        DataAccess.setPersonalHighscore(Snake.bestscore);
        if (Snake.head.getX() < 0 || Snake.head.getX() > 15 || Snake.head.getY() < 0 || Snake.head.getY() > 15) {
            GameClock.velocity = 200;
            DataAccess.loadLeaderboard();
            return true;
        }
        return false;
    }

    public static void colliedPickUp() {
        if (Snake.head.getX() == Snake.pickup.getX() && Snake.head.getY() == Snake.pickup.getY()) {

            if (Snake.score % 5 == 0) {
                GameClock.velocity -= 15;
            }

            Snake.pickup.reset();
            Snake.addTail();
            Snake.score++;
            if (Snake.score > Snake.bestscore) {
                Snake.bestscore = Snake.score;
            }
        }
    }

    public static void colliedMultiplier() {
        if (Snake.head.getX() == Snake.multiplier.getX() && Snake.head.getY() == Snake.multiplier.getY()) {

            GameClock.velocity += 10;

            Snake.multiplier.reset();
            Snake.addTail();
            Snake.score++;
            if (Snake.score > Snake.bestscore) {
                Snake.bestscore = Snake.score;
            }
        }
    }
}
