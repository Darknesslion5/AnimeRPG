package de.chris.rpg;

public class CollisionChecker {
    private TileManager tileM;

    public CollisionChecker(TileManager tileM) {
        this.tileM = tileM;
    }

    public boolean checkCollision(int nextX, int nextY) {
        int leftCol = nextX / 64;
        int rightCol = (nextX + 26) / 64;
        int topRow = nextY /64;
        int bottomRow = (nextY + 50) / 64;

        if (leftCol < 0
         || rightCol >= 17
         || topRow < 0
         || bottomRow >= 13) {
            return true;
        }

        int tileTopLeft = tileM.getMapTileNum()[leftCol][topRow];
        int tileTopRight = tileM.getMapTileNum()[rightCol][topRow];
        int tileBottomLeft = tileM.getMapTileNum()[leftCol][bottomRow];
        int tileBottomRight = tileM.getMapTileNum()[rightCol][bottomRow];

        if (tileM.getTile()[tileTopLeft].collision
         || tileM.getTile()[tileTopRight].collision
         || tileM.getTile()[tileBottomLeft].collision
         || tileM.getTile()[tileBottomRight].collision) {
            return true;
        }

        return false;
    }
}
