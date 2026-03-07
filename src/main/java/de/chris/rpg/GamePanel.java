package de.chris.rpg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable {
    private int playerX = 100;
    private int playerY = 100;
    private KeyHandler keyH = new KeyHandler();
    private BufferedImage playerImage;

    private Thread gameThread;

    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        getPlayerImage("fredolin");
    }

    private void getPlayerImage(String player) {
        try {
            playerImage = ImageIO.read(getClass().getResourceAsStream("/" + player + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(playerImage, this.playerX, this.playerY, 26, 50, null);
        g2.dispose();
    }
}
