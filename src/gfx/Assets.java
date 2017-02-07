package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

//ALL PRELOADED OBJECTS
public class Assets {
    //FILE SIZE DIVIDED BY TILES PER ROW/ HEIGHT (SPRITE.PNG)
    public static final int TILE_WIDTH = 500/16 -2, TILE_HEIGHT = 330/12;
    private static final int TREE_WIDTH = 65, TREE_HEIGHT = 95;

    private static final int HERO_WIDTH = 100, HERO_HEIGHT = 100;

    //IMAGE PATHS
    public static final String PATH = "./res/GameField/TileSamples-Uncut/sprite.png";
    private static final String MISC_PATH = "./res/GameField/TileSamples-Uncut/D2_spring_by_Candacis_zps8bvcasnr.png";
    private static final String HERO_PATH = "./res/Hero/heroechico_swd_AllPositions.png";

    //LIST ALL OBJECTS
    //STATIC
    public static BufferedImage grass, water, stone, tree;

    //ANIMATIONS
    public static BufferedImage[] playerDownMove, playerUpMove, playerLeftMove, playerRightMove;
    public static BufferedImage[] playerDownAttack, playerUpAttack, playerLeftAttack, playerRightAttack;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(PATH));
        SpriteSheet hero =  new SpriteSheet(ImageLoader.loadImage(HERO_PATH));
        SpriteSheet misc = new SpriteSheet(ImageLoader.loadImage(MISC_PATH));

        water = sheet.crop(0, 0, TILE_WIDTH, TILE_HEIGHT);
        grass = sheet.crop(3 * TILE_WIDTH, 2* TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        stone = sheet.crop(141, 45, TILE_WIDTH, TILE_HEIGHT);


        tree = misc.crop(1 * TREE_WIDTH,1 * TREE_HEIGHT, TREE_WIDTH, TREE_HEIGHT);
        //ANIMATIONS
        playerDownMove = new BufferedImage[2];
        playerUpMove = new BufferedImage[2];
        playerLeftMove = new BufferedImage[2];
        playerRightMove = new BufferedImage[2];
        playerDownAttack = new BufferedImage[2];
        playerUpAttack = new BufferedImage[2];
        playerLeftAttack = new BufferedImage[2];
        playerRightAttack = new BufferedImage[2];

        //MOVING
        playerDownMove[0] = hero.crop(0,0, HERO_WIDTH, HERO_HEIGHT);
        playerDownMove[1] = hero.crop(100, 0, HERO_WIDTH, HERO_HEIGHT);
        playerUpMove[0] = hero.crop(0,300, HERO_WIDTH, HERO_HEIGHT);
        playerUpMove[1] = hero.crop(100, 300, HERO_WIDTH, HERO_HEIGHT);
        playerLeftMove[0] = hero.crop(0,100, HERO_WIDTH, HERO_HEIGHT);
        playerLeftMove[1] = hero.crop(200, 100, HERO_WIDTH, HERO_HEIGHT);
        playerRightMove[0] = hero.crop(0,200, HERO_WIDTH, HERO_HEIGHT);
        playerRightMove[1] = hero.crop(200, 200, HERO_WIDTH, HERO_HEIGHT);
        //ATTACKING
        playerDownAttack[0] = hero.crop(300, 0, HERO_WIDTH, HERO_HEIGHT);
        playerDownAttack[1] = hero.crop(100, 0, HERO_WIDTH, HERO_HEIGHT);
        playerUpAttack[0] = hero.crop(100, 300, HERO_WIDTH, HERO_HEIGHT);
        playerUpAttack[1] = hero.crop(200, 300, HERO_WIDTH, HERO_HEIGHT);
        playerLeftAttack[0] = hero.crop(100, 100, HERO_WIDTH, HERO_HEIGHT);
        playerLeftAttack[1] = hero.crop(300, 100, HERO_WIDTH, HERO_HEIGHT);
        playerRightAttack[0] = hero.crop(100, 200, HERO_WIDTH, HERO_HEIGHT);
        playerRightAttack[1] = hero.crop(300, 200, HERO_WIDTH, HERO_HEIGHT);

    }
}
