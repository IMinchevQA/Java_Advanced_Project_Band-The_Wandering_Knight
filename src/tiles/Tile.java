package tiles;

import java.awt.*;
import java.awt.image.BufferedImage;
//TILE CLASS ALL TILES WILL EXTEND THIS CLASS
public class Tile {
    //SO WE DONT HAVE MAGIC NUMBERS DIFFERENT TILES IDS
    private static final int GRASS_ID = 0;
    private static final int WATER_ID = 1;//A
    private static final int STONE_ID = 2;//A

    //END TILES IDS

    //ARRAY WITH THE UNIQUE IDS OF ALL TYPE OF TILES WE WILL HAVE , 256 SHOULD BE MORE THAN ENOUGH
    public static Tile[] tiles = new Tile[256];
    //INITIALIZE ALL TYPES OF TILES HERE
    public static Tile dirtTile = new WaterTile(WATER_ID);//A
    public static Tile grassTile = new GrassTile(GRASS_ID);
    public static Tile rockTile = new StoneTile(STONE_ID);//A



    //END INITIALIZE TILES

    //BECAUSE OF THE WAY WE DO COLLISION HANDLING WE NEED BIGGER BLOCKS
    //BECAUSE WE ONLY CHECK FOR COLLISION THE 2 CORNERS OF THE BOUNDS RECTANGLE
    //AND IF THE COLLISION(BOUNDS) RECTANGLE TRIES TO GO THROUGH A TILE WITHOUT TOUCHING THE CORNER
    //COLLISION DOESNT WORK
    public static final int TILE_WIDTH = 42, TILE_HEIGHT = 42;

    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;

        //ADDING THE TILE ID TO THE TILES ARRAY, SO WE CAN ACCESS IT FOR RENDERING
        tiles[id] = this;
    }


    public void update() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean isWalkable() {
        return true;
    }

    public boolean isBreakable() {
        return false;
    }

    public int getId() {
        return this.id;
    }

}
