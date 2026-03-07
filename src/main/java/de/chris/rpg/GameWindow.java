package de.chris.rpg;

import javax.swing.*;

public class GameWindow {

    public GameWindow() {
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setTitle("AnimeRPG - Level 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        GamePanel gamePanel = new GamePanel();
        gamePanel.startGameThread();
        frame.add(gamePanel);
        frame.setVisible(true);
    }
}
