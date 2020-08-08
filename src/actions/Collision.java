package actions;

import database.DataAccess;
import game.Snake;

public class Collision {

    public static boolean colliedSelf() {
        DataAccess.setHighScore(Snake.bestscore);
        for (int i = 0; i < Snake.tails.size(); i++) {
            if (Snake.head.getX() == Snake.tails.get(i).getX() && Snake.head.getY() == Snake.tails.get(i).getY()
                    && !Snake.tails.get(i).isWait()) {
                return true;
            }
        }
        return false;
    }

    public static boolean colliedWall() {
        DataAccess.setHighScore(Snake.bestscore);
        return (Snake.head.getX() < 0 || Snake.head.getX() > 15 || Snake.head.getY() < 0 || Snake.head.getY() > 15);
    }

    public static void colliedPickUp() {
        if (Snake.head.getX() == Snake.pickup.getX() && Snake.head.getY() == Snake.pickup.getY()) {
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
            Snake.multiplier.reset();
            Snake.addTail();
            Snake.score++;
            if (Snake.score > Snake.bestscore) {
                Snake.bestscore = Snake.score;
            }
        }
    }
}
