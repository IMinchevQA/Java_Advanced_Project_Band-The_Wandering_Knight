package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;

//ALL PRELOADED OBJECTS
public class Assets {
    //FILE SIZE DIVIDED BY TILES PER ROW/ HEIGHT (SPRITE.PNG)
    public static final int TILE_WIDTH = 500/16 -2, TILE_HEIGHT = 330/12;

    //IMAGE PATHS
    public static final String PATH = "./res/GameField/TileSamples-Uncut/sprite.png";
    public static final String HERO_PATH = "./res/Hero/heroechico_swd_AllPositions.png";
//Path path = new Path("../res/Hero/hero_AllPositions.png");
    //LIST ALL OBJECTS
    public static BufferedImage player, grass, water, stone;
    public static BufferedImage[] player_Left, player_Right, player_Up, player_Down;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(PATH));
        SpriteSheet playerSheet =  new SpriteSheet(ImageLoader.loadImage(HERO_PATH));

//        player = hero.crop(0,0, 50, 50);
        player_Left = new BufferedImage[2];
        player_Right = new BufferedImage[2];
        player_Up = new BufferedImage[2];
        player_Down = new BufferedImage[2];
        player_Left[0] = playerSheet.crop(40, 142, 45, 58);
        player_Left[1] = playerSheet.crop(222, 150, 45, 50);
        player_Right[0] = playerSheet.crop(29, 245, 45, 55);
        player_Right[1] = playerSheet.crop(237, 252, 45, 50);
        player_Up[0] = playerSheet.crop(23, 350, 41, 49);
        player_Up[1] = playerSheet.crop(113, 348, 50, 49);
        player_Down[0] = playerSheet.crop(120, 50, 45, 50);
        player_Down[1] = playerSheet.crop(211, 50, 50, 50);
        water = sheet.crop(0, 0, TILE_WIDTH, TILE_HEIGHT);
        grass = sheet.crop(3 * TILE_WIDTH, 2* TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        stone = sheet.crop(141, 45, TILE_WIDTH, TILE_HEIGHT);

    }
}
