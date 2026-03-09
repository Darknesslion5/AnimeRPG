package de.chris.rpg;

import java.awt.*;

public class UI {
    private boolean messageOn = false;
    private String message = "";
    private int messageCounter = 0;

    private Font standardFont = new Font("Arial", Font.PLAIN, 20);
    private Font winFont = new Font("Arial", Font.BOLD, 60);

    public void showMessage(String text) {
        message = text;
        messageOn = true;
        messageCounter = 0;
    }

    public void draw(Graphics2D g2, boolean gameWon) {
        if (gameWon) {
            g2.setFont(winFont);
            g2.setColor(Color.YELLOW);
            g2.drawString("LEVEL CLEARED!", 300, 400);
        } else {
            if (messageOn) {
                g2.setFont(standardFont);
                g2.setColor(Color.WHITE);
                g2.drawString(message, 20, 50);
                messageCounter ++;
                if (messageCounter > 150) {
                    messageOn = false;
                    messageCounter = 0;
                }
            }
        }
    }
}
