package gui;

import actions.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class Gui implements Runnable {

    public static int width = 930, height = 600;
    public static int xoff = 200, yoff = 20;
    JFrame jf;
    Draw d;

    public void create() {
        jf = new JFrame("Snake");
        jf.setSize(width, height);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setLayout(new BorderLayout());
        jf.setResizable(false);
        jf.addKeyListener(new KeyHandler());

        JMenuBar menuBar = new JMenuBar();
        menuBar.setVisible(true);

        JMenu fileMenu = new JMenu("File");
        fileMenu.setVisible(true);
        JMenu editMenu = new JMenu("Edit");
        editMenu.setVisible(true);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        jf.add(menuBar);

        d = new Draw();
        d.setBounds(0, 0, width, height);
        d.setVisible(true);
        jf.add(d);

        jf.requestFocus();
        jf.setVisible(true);

    }

    @Override
    public void run() {
        create();
    }
}
