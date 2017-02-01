package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

//ALL PRELOADED OBJECTS
public class Assets {
    //FILE SIZE DIVIDED BY TILES PER ROW/ HEIGHT (SPRITE.PNG)
    public static final int TILE_WIDTH = 500/16 -2, TILE_HEIGHT = 330/12;

    //IMAGE PATHS
    public static final String PATH = "./res/GameField/TileSamples-Uncut/sprite.png";
    public static final String HERO_PATH = "./res/Hero/Move_Down/01_MoveDown_Odd.png";

    //LIST ALL OBJECTS
    public static BufferedImage player, grass, water, stone;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(PATH));
        SpriteSheet hero =  new SpriteSheet(ImageLoader.loadImage(HERO_PATH));
        player = hero.crop(0,0, 50, 50);
        water = sheet.crop(0, 0, TILE_WIDTH, TILE_HEIGHT);
        grass = sheet.crop(3 * TILE_WIDTH, 2* TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        stone = sheet.crop(4 * TILE_WIDTH, 0, TILE_WIDTH, TILE_HEIGHT);

    }
}
