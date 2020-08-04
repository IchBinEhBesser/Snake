package gui;

import database.DataAccess;
import game.Snake;

import javax.swing.*;
import java.awt.*;

public class Draw extends JLabel {

    Point p;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);

        //Draw Background
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, Gui.width, Gui.height);

        //Draw snaktails
        g.setColor(new Color(51,204,51));
        for(int i = 0; i < Snake.tails.size(); i++){
            p = Snake.ptc(Snake.tails.get(i).getX(), Snake.tails.get(i).getY());
            g.fillRect(p.x,p.y,32,32);
        }

        //Draw Snake Head
        g.setColor(new Color(0,153,0));
        p = Snake.ptc(Snake.head.getX(),Snake.head.getY());
        g.fillRect(p.x,p.y,32,32);

        //Draw PickUp
        g.setColor(new Color(204,51,0));
        p = Snake.ptc(Snake.pickup.getX(), Snake.pickup.getY());
        g.fillRect(p.x,p.y,32,32);

        //Draw Grid
        g.setColor(Color.gray);
        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
                g.drawRect(i*32+Gui.xoff, j*32+Gui.yoff,32,32);
            }
        }

        //Draw Border
        g.setColor(Color.black);
        g.drawRect(Gui.xoff, Gui.yoff,512,512);

        //Draw Score
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: "+Snake.score, 5, 25);
        g.drawString("1st: "+ DataAccess.getLeaderScore(0), 655, 25);
        g.drawString("2nd: "+ DataAccess.getLeaderScore(1), 655, 50);
        g.drawString("3th: "+ DataAccess.getLeaderScore(2), 655, 75);



        repaint();
    }
}
