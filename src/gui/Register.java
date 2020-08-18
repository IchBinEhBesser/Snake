package gui;

import database.DataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register {

    public static String playerName;
    public static String playerPassword;
    private final int width = 100;
    private final int height = 75;
    public static JFrame jFrame;

    public static JLabel regInfo = new JLabel("Benutzer existiert schon");


    public void create() {

        jFrame = new JFrame("Snake-Registration");
        jFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        jFrame.setSize(250, 175);
        jFrame.setResizable(true);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        JTextField unField = new JTextField("Username");
        unField.setPreferredSize(new Dimension(200, 24));
        unField.setVisible(true);
        jFrame.add(unField);

        JTextField pwField = new JTextField("Password");
        pwField.setPreferredSize(new Dimension(200, 24));
        pwField.setVisible(true);
        jFrame.add(pwField);

        regInfo.setPreferredSize(new Dimension(215, 10));
        regInfo.setAlignmentX(FlowLayout.CENTER);
        regInfo.setForeground(Color.red);
        regInfo.setVisible(false);
        jFrame.add(regInfo);

        JButton regButton = new JButton("Register");
        regButton.setFocusPainted(false);
        regButton.setSize(100, 50);
        regButton.setVisible(true);
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataAccess.registradePlayer(unField.getText(), pwField.getText());
            }
        });
        jFrame.add(regButton);

        jFrame.setVisible(true);

    }
}
