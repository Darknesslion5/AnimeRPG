package de.chris.rpg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {
    private BufferedImage image;
    private int x, y;
    private boolean collected = false;

    public GameObject(String imagePath, int startX, int startY) {
        this.x = startX;
        this.y = startY;
        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCollected() {
        return collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public void draw(Graphics2D g2) {
        if (!collected) {
            g2.drawImage(image, x, y, 64, 64, null);
        }
    }
}
