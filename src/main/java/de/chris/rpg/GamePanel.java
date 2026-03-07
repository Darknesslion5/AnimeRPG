package de.chris.rpg;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    private int playerX = 100;
    private int playerY = 100;
    private KeyHandler keyH = new KeyHandler();

    private Thread gameThread;

    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            if (keyH.upPressed) {
                this.playerY -= 4;
            }
            if (keyH.leftPressed) {
                this.playerX -= 4;
            }
            if (keyH.downPressed) {
                this.playerY += 4;
            }
            if (keyH.rightPressed) {
                this.playerX += 4;
            }
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(this.playerX, this.playerY, 50, 50);
    }
}
