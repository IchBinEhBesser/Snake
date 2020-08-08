package gui;

import database.DataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login {

    public static String playerName;
    public static String playerPassword;
    private final int width = 100;
    private final int height = 75;
    JFrame jFrame;

    public void create() {

        jFrame = new JFrame("Snake-Login");
        jFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        jFrame.setSize(250, 150);
        jFrame.setResizable(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        JTextField unField = new JTextField("Username");
        unField.setPreferredSize(new Dimension(200, 24));
        unField.setVisible(true);
        jFrame.add(unField);

        JTextField pwField = new JTextField("Password");
        pwField.setPreferredSize(new Dimension(200, 24));
        pwField.setVisible(true);
        jFrame.add(pwField);

        JButton regButton = new JButton("Register");
        regButton.setFocusPainted(false);
        regButton.setSize(100, 50);
        regButton.setVisible(true);
        jFrame.add(regButton);

        JButton logButton = new JButton("Login");
        logButton.setFocusPainted(false);
        logButton.setSize(100, 50);
        logButton.setVisible(true);
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataAccess.checkPlayer(unField.getText(), pwField.getText());
            }
        });
        jFrame.add(logButton);

        jFrame.setVisible(true);


    }

}
