package de.chris.rpg;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:savegame.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Error: database connection");
            e.printStackTrace();
        }
        return conn;
    }

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS players ("
                   + "name TEXT PRIMARY KEY,"
                   + "x INTEGER,"
                   + "y INTEGER"
                   + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt. execute(sql);
            System.out.println("table 'players' is ready.");
        } catch (SQLException e) {
            System.out.println("Error: table creation");
            e.printStackTrace();
        }
    }

    public static void savePlayer(String name, int x, int y) {
        String sql = "INSERT OR REPLACE INTO players(name, x, y) VALUES(?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString (1, name);
            pstmt.setInt(2, x);
            pstmt.setInt(3, y);
            pstmt.executeUpdate();
            System.out.println("Saved: " + name);
        } catch (SQLException e) {
            System.out.println("Error: saving");
            e.printStackTrace();
        }
    }

    public static int[] loadPlayer(String name) {
        String sql = "SELECT x, y FROM players WHERE name = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int savedX = rs.getInt("x");
                int savedY = rs.getInt("y");
                System.out.println("Loaded: " + name);
                return new int[] {savedX, savedY};
            }
            System.out.println("No save state for: " + name);
        } catch (SQLException e) {
            System.out.println("Error: loading");
            e.printStackTrace();
        }
        return null;
    }
}
