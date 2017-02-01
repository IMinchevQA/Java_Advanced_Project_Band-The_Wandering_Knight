package world;

import tiles.Tile;
import utils.Utils;

import javax.rmi.CORBA.Util;
import java.awt.*;
//SEE RES/WORLD/WORLD.TXT FOR MORE INFO
public class World {

    //WORLD WIDTH AND HEIGHT, HERO SPAWN COORDINATES WILL BE INITIALIZED IN LOADWORLD METHOD FROM OUR WORLD FILE
    private int width, height;
    private int spawnX, spawnY;

    private int[][] tiles;

    public World(String path) {
        loadWorld(path);
    }

    public void update() {

    }

    public void render(Graphics g) {
        //ITERATE THROUGH THE TILES ARRAY AND RENDER
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
               getTile(x, y).render(g, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT);
            }
        }
    }

    public Tile getTile(int x, int y) {
        Tile t = Tile.tiles[tiles[x][y]];
        //IF WE CALL WITH EMPTY MATRIX INDEX RETURN GRASSTILE
        if (t == null)
            return Tile.grassTile;
        return t;
    }

    private void loadWorld(String path) {
        String file = Utils.loadFileAsString(path);
        //ONE DIMENSIONAL ARRAY WITH OUR FILE INFO
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                //ASSINGING EVERY INDEX TO THE ARRAY
                tiles[x][y] = Utils.parseInt(tokens[(x+y*width) + 4]);
            }
        }
    }
}
