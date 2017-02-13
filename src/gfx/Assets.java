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
    public static final String START_BUTTON = "./res/Buttons/Start-2-icon.png";
    public static final String START_BUTTON_ANIM = "./res/Buttons/Start-icon.png";
    public static final String VILLAIN_PATH = "./res/Monster/Monster_uncut.png";

    //Path path = new Path("../res/Hero/hero_AllPositions.png");
    //LIST ALL OBJECTS
    public static BufferedImage player, grass, water, stone,
            w_lilly,w_stone,mushroom1,mushroom2,
            bush1,bush2,bush3,flower1,well_full,flower2,
            stone_wall_up1,stone_wall_up2,stone_wall_down1,stone_wall_down2,
            stone_wall_left,stone_wall_right, tree1, tree2, tree3, forest, cutDownTree;
    public static BufferedImage[] player_Left, player_Right, player_Up, player_Down;
    public static BufferedImage[] villain_Left, villain_Right, villain_Up, villain_Down;
    public static BufferedImage[] btn_start;
    public static BufferedImage player_DownStill, player_UpStill, player_RightStill, player_LeftStill;
    public static BufferedImage villains_DownFighting, villains_UpFighting, villains_RightFighting, villains_LeftFighting;

    public static void init() {
        SpriteSheet button1 = new SpriteSheet(ImageLoader.loadImage(START_BUTTON));
        SpriteSheet button2 = new SpriteSheet(ImageLoader.loadImage(START_BUTTON_ANIM));
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(PATH));
        SpriteSheet playerSheet =  new SpriteSheet(ImageLoader.loadImage(HERO_PATH));
        SpriteSheet trees = new SpriteSheet(ImageLoader.loadImage(TREES_PATH));
        SpriteSheet villains = new SpriteSheet(ImageLoader.loadImage(VILLAIN_PATH));

//        player = hero.crop(0,0, 50, 50);
        btn_start = new BufferedImage[2];
        btn_start[0] = button1.crop(0, 0, 256, 256);
        btn_start[1] = button2.crop(0, 0, 256, 256);
        player_Left = new BufferedImage[2];
        player_Right = new BufferedImage[2];
        player_Up = new BufferedImage[2];
        player_Down = new BufferedImage[2];
        player_Left[0] = playerSheet.crop(28, 144, 45, 55);
        player_Left[1] = playerSheet.crop(222, 150, 45, 50);
        player_Right[0] = playerSheet.crop(20, 245, 45, 55);
        player_Right[1] = playerSheet.crop(229, 252, 45, 50);
        player_Up[0] = playerSheet.crop(26, 350, 45, 49);
        player_Up[1] = playerSheet.crop(116, 348, 50, 49);
        player_Down[0] = playerSheet.crop(120, 50, 45, 50);
        player_Down[1] = playerSheet.crop(211, 50, 50, 50);
        player_DownStill = playerSheet.crop(20, 50, 45, 50);
        player_UpStill = playerSheet.crop(324, 350, 53, 50);
        player_RightStill = playerSheet.crop(330, 250, 50, 50);
        player_LeftStill = playerSheet.crop(315, 150, 50, 50);

        villain_Left = new BufferedImage[2];
        villain_Right = new BufferedImage[2];
        villain_Up = new BufferedImage[2];
        villain_Down = new BufferedImage[2];
        villain_Left[0] = villains.crop(7, 64, 59, 64);
        villain_Left[1] = villains.crop(134, 64, 59, 64);
        villains_LeftFighting = villains.crop(70, 64, 59, 64);
        villain_Right[0] = villains.crop(7, 128, 59, 64);
        villain_Right[1] = villains.crop(134, 128, 59, 64);
        villains_RightFighting = villains.crop(70, 128, 59, 64);
        villain_Up[0] = villains.crop(4, 193, 59, 64);
        villain_Up[1] = villains.crop(132, 193, 59, 64);
        villains_UpFighting = villains.crop(69, 193, 59, 64);
        villain_Down[0] = villains.crop(4, 0, 59, 64);
        villain_Down[1] = villains.crop(132, 0, 59, 64);
        villains_DownFighting = villains.crop(69, 0, 59, 64);

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
        cutDownTree = trees.crop(69, 385, 57,56);
    }
}
