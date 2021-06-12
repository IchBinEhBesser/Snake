package gui;

import clocks.GameClock;
import database.DataAccess;
import game.Snake;

import javax.swing.*;
import java.awt.*;

public class Draw extends JLabel {

    Point p;

    double firstFrame;
    int frames;
    double currentFrame;
    int fps;

    /*
    public float fpsCap = 60;
    public float timePerFrameInSeconds = 1 / fpsCap; //time per frame in seconds
    public float timePerFrameInNanos = timePerFrameInSeconds * 1000000000;

    public double nowTime = 0;
    public double preTime = 0;
    public double timeDif = 0;

     */

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        //init Graphics
        initG2D(g);

        //Draw Background
        drawBackground(g);

        //Draw snaktails
        drawSnakeTails(g);

        //Draw Snake Head
        drawSnakeHead(g);

        //Draw PickUp
        drawPickUp(g);

        drawMultiplicator(g);

        //Draw Grid
        drawGrid(g);

        //Draw Border
        drawBorder(g);

        //nun in paint() / update() bzw. paintComponent() ...
        calculateFramesPerSecond();

        //Draw other information
        drawGameInformations(g);
        drawScoreBoard(g);

        //setFrameCapture();

        repaint();
    }

    private void initG2D(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, Gui.width, Gui.height);
    }

    private void drawSnakeTails(Graphics g) {
        g.setColor(new Color(51, 204, 51));
        for (int i = 0; i < Snake.tails.size(); i++) {
            p = Snake.ptc(Snake.tails.get(i).getX(), Snake.tails.get(i).getY());
            g.fillRect(p.x, p.y, 32, 32);
        }
    }

    private void drawSnakeHead(Graphics g) {
        g.setColor(new Color(0, 153, 0));
        p = Snake.ptc(Snake.head.getX(), Snake.head.getY());
        g.fillRect(p.x, p.y, 32, 32);
    }

    private void drawPickUp(Graphics g) {
        g.setColor(new Color(204, 51, 0));
        p = Snake.ptc(Snake.pickup.getX(), Snake.pickup.getY());
        g.fillRect(p.x, p.y, 32, 32);
    }

    private void drawMultiplicator(Graphics g) {
        if (Snake.score % 5 == 0) {
            //Draw Mulitplikator
            g.setColor(new Color(239, 216, 12));
            p = Snake.ptc(Snake.multiplier.getX(), Snake.multiplier.getY());
            g.fillRect(p.x, p.y, 32, 32);
        }
    }

    private void drawGrid(Graphics g) {
        g.setColor(Color.gray);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                g.drawRect(i * 32 + Gui.xoff, j * 32 + Gui.yoff, 32, 32);
            }
        }
    }

    private void drawBorder(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(Gui.xoff, Gui.yoff, 512, 512);
    }

    private void calculateFramesPerSecond() {
        frames++;
        currentFrame = System.currentTimeMillis();
        if (currentFrame > firstFrame + 1000) {
            firstFrame = currentFrame;
            fps = frames;
            frames = 0;
        }
    }

    private void drawGameInformations(Graphics g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player: " + Login.playerName, 5, 25);
        g.drawString("Score: " + Snake.score, 5, 50);
        g.drawString("Velocity: " + GameClock.velocity, 5, 75);
        g.drawString("FPS: " + fps, 5, 100);

    }

    public void drawScoreBoard(Graphics g) {

        for (int i = 0; i < DataAccess.getPlayerCount(); i++) {
            g.drawString((i + 1) + "st: " + DataAccess.getLeaderboard()[i], 730, 25 + i * 25);
        }

    }

    /*
    private void setFrameCapture() {

        do{
            preTime = nowTime;
            nowTime = System.nanoTime();
            timeDif = nowTime - preTime; //time difference in nanos

            System.out.println("PreTime: " + preTime + " ns");

            System.out.println("NowTime: " + nowTime + " ns");

            System.out.println("Time allready over: " + (timePerFrameInNanos - timeDif) + " ns");

            System.out.println("Time to wait from here: " + timeDif + " ns");

            System.out.println("timePerFrameInSeconds: " + timePerFrameInSeconds + " s");

            System.out.println("timePerFrameInNanos: " + timePerFrameInNanos + " ns");

            System.out.println();

        }while(timeDif < (timePerFrameInNanos - timeDif));

    }
    */

}