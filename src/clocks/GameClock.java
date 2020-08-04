package clocks;

import actions.Collison;
import game.Snake;

public class GameClock extends Thread{
    public static boolean running = true;
    public void run(){
        while(running){
            try {
                sleep(200);
                Snake.move();
                Snake.waitToMove = false;
                Collison.colliedPickUp();
                if(Collison.colliedSelf()){
                    Snake.tails.clear();
                    Snake.score = 0;
                }
                if(Collison.colliedWall()){
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
