package de.chris.rpg;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.createTable();
        GameWindow window = new GameWindow();
    }
}
