package tiles;

import tiles.desert.Cactus1;
import tiles.desert.Cactus2;
import tiles.desert.Sand;
import tiles.greenland.GrassTile;
import tiles.greenland.StoneTile;
import tiles.greenland.WaterTile;
import tiles.greenland.decorations.*;
import tiles.greenland.stone_wall.*;
import tiles.iceland.SnowTile;
import tiles.iceland.SnowyBush;

import java.awt.*;
import java.awt.image.BufferedImage;
//TILE CLASS ALL TILES WILL EXTEND THIS CLASS
public class Tile {
    //SO WE DONT HAVE MAGIC NUMBERS DIFFERENT TILES IDS
    private static final int GRASS_ID = 0;
    private static final int WATER_ID = 1;//A
    private static final int STONE_ID = 2;//A
//new
    private static final int W_LILLY_ID = 3;
    private static final int W_STONE_ID = 4;
    private static final int MUSHROOM1_ID = 5;
    private static final int MUSHROOM2_ID = 6;
    private static final int BUSH1_ID = 7;
    private static final int BUSH2_ID = 8;
    private static final int BUSH3_ID = 9;
    private static final int FLOWER1_ID = 10;
    private static final int WELL_FULL_ID = 11;
    private static final int FLOWER2_ID = 12;

    private static final int STONE_WALL_UP1_ID = 20;
    private static final int STONE_WALL_UP2_ID = 21;
    private static final int STONE_WALL_DOWN1_ID = 22;
    private static final int STONE_WALL_DOWN2_ID = 23;
    private static final int STONE_WALL_LEFT_ID = 24;
    private static final int STONE_WALL_RIGHT_ID = 25;
    //iceland
    private static final int SNOW_ID = 30;
    private static final int SNOW_BUSH_ID = 31;
    //desert
    private static final int SAND_ID = 40;
    private static final int CACTUS1_ID = 41;
    private static final int CACTUS2_ID = 42;
    //END TILES IDS

    //ARRAY WITH THE UNIQUE IDS OF ALL TYPE OF TILES WE WILL HAVE , 256 SHOULD BE MORE THAN ENOUGH
    public static Tile[] tiles = new Tile[256];
    //INITIALIZE ALL TYPES OF TILES HERE
    public static Tile dirtTile = new WaterTile(WATER_ID);//A
    public static Tile grassTile = new GrassTile(GRASS_ID);
    public static Tile rockTile = new StoneTile(STONE_ID);//A
    //new
    public static Tile w_lilly = new W_lilly(W_LILLY_ID);
    public static Tile w_stone = new W_stone(W_STONE_ID);
    public static Tile mushroom1 = new Mushroom1(MUSHROOM1_ID);
    public static Tile mushroom2 = new Mushroom2(MUSHROOM2_ID);
    public static Tile bush1 = new Bush1(BUSH1_ID);
    public static Tile bush2 = new Bush2(BUSH2_ID);
    public static Tile bush3 = new Bush3(BUSH3_ID);
    public static Tile flower1 = new Flower1(FLOWER1_ID);
    public static Tile well_full = new Well_full(WELL_FULL_ID);
    public static Tile flower2 = new Flower2(FLOWER2_ID);

    public static Tile stone_wall_up1 = new Stone_wall_up1(STONE_WALL_UP1_ID);
    public static Tile stone_wall_up2 = new Stone_wall_up2(STONE_WALL_UP2_ID);
    public static Tile stone_wall_down1 = new Stone_wall_down1(STONE_WALL_DOWN1_ID);
    public static Tile stone_wall_down2 = new Stone_wall_down2(STONE_WALL_DOWN2_ID);
    public static Tile stone_wall_left = new Stone_wall_left(STONE_WALL_LEFT_ID);
    public static Tile stone_wall_right = new Stone_wall_right(STONE_WALL_RIGHT_ID);

    //iceland
    public static Tile snowTile = new SnowTile(SNOW_ID);
    public static Tile snowyBushTile = new SnowyBush(SNOW_BUSH_ID);
    //desart
    public static Tile sandTile = new Sand(SAND_ID);
    public static Tile cactus1Tile = new Cactus1(CACTUS1_ID);
    public static Tile cactus2Tile = new Cactus2(CACTUS2_ID);
    //END INITIALIZE TILES

    public static final int TILE_WIDTH = 32, TILE_HEIGHT = 32;

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

    public boolean isSolid() {
        return false;
    }

    public boolean isBreakable() {
        return false;
    }

    public int getId() {
        return this.id;
    }

}
