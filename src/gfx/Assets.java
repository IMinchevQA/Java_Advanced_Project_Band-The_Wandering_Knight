package gfx;

import java.awt.image.BufferedImage;

//ALL PRELOADED OBJECTS
public class Assets {
    //FILE SIZE DIVIDED BY TILES PER ROW/ HEIGHT (SPRITE.PNG)
    public static final int TILE_WIDTH = 500/16 -2, TILE_HEIGHT = 330/12;
    private static final int TREE_WIDTH = 63, TREE_HEIGHT = 97;

    //IMAGE PATHS
    public static final String PATH = "./res/GameField/TileSamples-Uncut/sprite.png";
    public static final String HERO_PATH = "./res/Hero/heroechico_swd_AllPositions.png";
    public static final String TREES_PATH = "./res/GameField/TileSamples-Uncut/Trees.png";

    //Path path = new Path("../res/Hero/hero_AllPositions.png");
    //LIST ALL OBJECTS
    public static BufferedImage player, grass, water, stone,
            w_lilly,w_stone,mushroom1,mushroom2,
            bush1,bush2,bush3,flower1,well_full,flower2,
            stone_wall_up1,stone_wall_up2,stone_wall_down1,stone_wall_down2,
            stone_wall_left,stone_wall_right, tree1, tree2, tree3, forest;
    public static BufferedImage[] player_Left, player_Right, player_Up, player_Down;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(PATH));
        SpriteSheet playerSheet =  new SpriteSheet(ImageLoader.loadImage(HERO_PATH));
        SpriteSheet trees = new SpriteSheet(ImageLoader.loadImage(TREES_PATH));

//        player = hero.crop(0,0, 50, 50);
        player_Left = new BufferedImage[2];
        player_Right = new BufferedImage[2];
        player_Up = new BufferedImage[2];
        player_Down = new BufferedImage[2];
        player_Left[0] = playerSheet.crop(28, 142, 45, 58);
        player_Left[1] = playerSheet.crop(222, 150, 45, 50);
        player_Right[0] = playerSheet.crop(20, 245, 45, 55);
        player_Right[1] = playerSheet.crop(229, 252, 45, 50);
        player_Up[0] = playerSheet.crop(23, 350, 41, 49);
        player_Up[1] = playerSheet.crop(113, 348, 50, 49);
        player_Down[0] = playerSheet.crop(120, 50, 45, 50);
        player_Down[1] = playerSheet.crop(211, 50, 50, 50);
        water = sheet.crop(0, 0, TILE_WIDTH, TILE_HEIGHT);
        grass = sheet.crop(3 * TILE_WIDTH, 2* TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        stone = sheet.crop(141, 45, TILE_WIDTH, TILE_HEIGHT);

        //new
        w_lilly = sheet.crop(15,395, TILE_WIDTH+10, TILE_HEIGHT+10);
        w_stone = sheet.crop(15,525, TILE_WIDTH+5, TILE_HEIGHT+5);
        mushroom1 = sheet.crop(85,438, TILE_WIDTH+5, TILE_HEIGHT+5);
        mushroom2 = sheet.crop(78,530, TILE_WIDTH+10, TILE_HEIGHT+10);
        bush1 = sheet.crop(150, 430, TILE_WIDTH+5, TILE_HEIGHT+5);
        bush2 = sheet.crop(150, 535, TILE_WIDTH+5, TILE_HEIGHT+5);
        bush3 = sheet.crop(270, 430, TILE_WIDTH+5, TILE_HEIGHT+5);
        flower1 = sheet.crop(210, 430, TILE_WIDTH+10, TILE_HEIGHT+10);
        well_full = sheet.crop(210, 525, TILE_WIDTH+5, TILE_HEIGHT+5);
        flower2 = sheet.crop(395, 520, TILE_WIDTH+5, TILE_HEIGHT+5);

        stone_wall_up1 = sheet.crop(438, 505-TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        stone_wall_up2 = sheet.crop(435+TILE_WIDTH, 505-TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT);
        stone_wall_down1 = sheet.crop(445, 450, TILE_WIDTH, TILE_HEIGHT);
        stone_wall_down2 = sheet.crop(445+TILE_WIDTH, 450, TILE_WIDTH, TILE_HEIGHT);
        stone_wall_left = sheet.crop(510-TILE_WIDTH, 398, TILE_WIDTH-5, TILE_HEIGHT-5);
        stone_wall_right = sheet.crop(500, 400, TILE_WIDTH-5, TILE_HEIGHT-5);


        tree1 = trees.crop(0, 0, TREE_WIDTH, TREE_HEIGHT);
        tree2 = trees.crop(TILE_WIDTH, 0, TREE_WIDTH, TREE_HEIGHT);
        tree3 = trees.crop(TREE_WIDTH * 2, 0, TREE_WIDTH, TREE_HEIGHT);
        forest = trees.crop(385, 290, 120, 220);
    }
}
