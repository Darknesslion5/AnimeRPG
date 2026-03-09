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
    private TileManager tileM = new TileManager();
    private GameObject chest = new GameObject("/chest.png", 300, 300);
    private UI ui = new UI();
    private boolean gameWon = false;

    private Thread gameThread;
    private CollisionChecker cChecker = new CollisionChecker(tileM);


    public GamePanel() {
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        getPlayerImage("fredolin");
        int[] savedStats = DatabaseManager.loadPlayer("fredolin");
        if (savedStats != null) {
            this.playerX = savedStats[0];
            this.playerY = savedStats[1];
        }
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
        if (!gameWon) {
            if (keyH.upPressed) {
                if (!cChecker.checkCollision(this.playerX, this.playerY - 4)) {
                    this.playerY -= 4;
                }
            }
            if (keyH.leftPressed) {
                if (!cChecker.checkCollision(this.playerX - 4, this.playerY)) {
                    this.playerX -= 4;
                }
            }
            if (keyH.downPressed) {
                if (!cChecker.checkCollision(this.playerX, this.playerY + 4)) {
                    this.playerY += 4;
                }
            }
            if (keyH.rightPressed) {
                if (!cChecker.checkCollision(this.playerX + 4, this.playerY)) {
                    this.playerX += 4;
                }
            }
        }
        if (keyH.savePressed) {
            DatabaseManager.savePlayer("fredolin", this.playerX, this.playerY);
            ui.showMessage("Game saved");
            keyH.savePressed = false;
        }

        Rectangle playerHitbox = new Rectangle(this.playerX, this.playerY, 26, 50);
        Rectangle chestHitbox = new Rectangle(chest.getX(), chest.getY(), 64, 64);
        if (!chest.isCollected() && playerHitbox.intersects(chestHitbox)) {
            chest.setCollected(true);
            gameWon = true;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        chest.draw(g2);
        g2.drawImage(playerImage, this.playerX, this.playerY, 26, 50, null);
        ui.draw(g2, gameWon);
        g2.dispose();
    }
}
