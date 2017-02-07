package world;

import game.Handler;
import tiles.Tile;
import utils.Utils;

import java.awt.*;

//SEE RES/WORLD/WORLD.TXT FOR MORE INFO
public class World {

    //WORLD WIDTH AND HEIGHT, HERO SPAWN COORDINATES WILL BE INITIALIZED IN LOADWORLD METHOD FROM OUR WORLD FILE
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tilesWorldMatrix;

    public World(Handler handler, String path) {
        this.handler = handler;
        //THE path PARAMETER IS PASSED BY GameState.java LINE Nr. -19!!!
        loadWorld(path);
    }

    public void update() {

    }

    public void render(Graphics g) {
        //xStart AND yStart CONTAIN THE Most-Top-Left Tile THAT THE USER CAN CURRENTLY SEE ON THE SCREEN.
        //xEnd AND yEnd CONTAIN THE Most-Bottom-Right Tile THAT THE USER CAN CURRENTLY SEE ON THE SCREEN.
        //THE PURPOSE IS TO render ONLY TILES VISIBLE ON DISPLAY.
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        //ITERATE THROUGH THE TILES ARRAY AND RENDER
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                                         (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }
    }

    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tilesWorldMatrix[x][y]];
        //IF WE CALL WITH EMPTY MATRIX INDEX RETURN GRASSTILE
        if (t == null)
            return Tile.grassTile;
        return t;
    }

    private void loadWorld(String path) {
        String worldFile = Utils.loadFileAsString(path);
        //ONE DIMENSIONAL ARRAY WITH OUR FILE INFO
        String[] tokens = worldFile.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tilesWorldMatrix = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                //ASSIGNING EVERY INDEX TO THE ARRAY
                //NUMBER 4 IS ADDED BECAUSE FIRST 4 ELEMENTS VALUES ARE ASSIGNED TO - width, height, spawnX, spawnY
                tilesWorldMatrix[x][y] = Utils.parseInt(tokens[(x+y*width) + 4]);
            }
        }
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

}
