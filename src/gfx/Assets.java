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
    public static final String START_SCREEN = "./res/Buttons/StartScreen.png";
    public static final String INFO_TEAM = "./res/Buttons/aboutINFO.png";
    public static final String START_BUTTON = "./res/Buttons/startGame.png";
    public static final String START_BUTTON_HOVERED = "./res/Buttons/startGameRed.png";
    public static final String ABOUT_BUTTON = "./res/Buttons/aboutBlack.png";
    public static final String ABOUT_BUTTON_HOVERED = "./res/Buttons/aboutRed.png";
    public static final String QUIT_BUTTON = "./res/Buttons/quitBlack.png";
    public static final String QUIT_BUTTON_HOVERED = "./res/Buttons/quitRed.png";
    public static final String BACK_BUTTON = "./res/Buttons/blackBack.png";
    public static final String BACK_BUTTON_HOVERED = "./res/Buttons/backRed.png";
    public static final String VILLAIN_PATH = "./res/Monster/Monster_uncut.png";
    public static final String CHASER_VILLAIN_PATH = "./res/Monster/Monster_uncut.png";
    public static final String END_GAME = "./res/game-over-png-20.png";
    public static final String PlAYER_HEALTH = "./res/Hero/heart.png";
    public static final String INVENTORY = "./res/GameField/inventory.png";
    public static final String COIN = "./res/GameField/coin.png";
    public static final String STONE1 = "./res/GameField/stone1.png";
    public static final String ROCKITEM = "./res/GameField/rockItem.png";
    public static final String MEAT = "./res/GameField/meat.png";
    public static final String SHEEP = "./res/Sheep/sheep_walk.png";

    //Path path = new Path("../res/Hero/hero_AllPositions.png");
    //LIST ALL OBJECTS
    public static BufferedImage player, grass, water, stone,
            w_lilly,w_stone,mushroom1,mushroom2,
            bush1,bush2,bush3,flower1,well_full,flower2,
            stone_wall_up1,stone_wall_up2,stone_wall_down1,stone_wall_down2,
            stone_wall_left,stone_wall_right, tree1, tree2, tree3, forest, cutDownTree, endGame;
    public static BufferedImage[] player_Left, player_Right, player_Up, player_Down;
    public static BufferedImage[] startScreen;
    public static BufferedImage[] infoTeam;
    public static BufferedImage[] btn_start;
    public static BufferedImage[] btn_about;
    public static BufferedImage[] btn_quit;
    public static BufferedImage[] btn_back;

    public static BufferedImage[] villain_Left, villain_Right, villain_Up, villain_Down;
    public static BufferedImage player_DownStill, player_UpStill, player_RightStill, player_LeftStill;
    public static BufferedImage[] chaserVillain_Left, chaserVillain_Right, chaserVillain_Up, chaserVillain_Down;
    public static BufferedImage villains_DownFighting, villains_UpFighting, villains_RightFighting, villains_LeftFighting;
    public static BufferedImage villains_DownStill, villains_UpStill, villains_RightStill, villains_LeftStill;
    public static BufferedImage chaserVillains_DownStill, chaserVillains_UpStill, chaserVillains_RightStill, chaserVillains_LeftStill;
    public static BufferedImage playerHealth, inventory, coin, rock1,rockItem, meat;
    public static BufferedImage[] sheep_Left, sheep_Right, sheep_Up, sheep_Down;
    public static BufferedImage sheep_LeftStiil, sheep_RightStill, sheep_UpStill, sheep_DownStill;

    public static void init() {
        SpriteSheet screen = new SpriteSheet(ImageLoader.loadImage(START_SCREEN));
        SpriteSheet info = new SpriteSheet(ImageLoader.loadImage(INFO_TEAM));
        SpriteSheet buttonNormal = new SpriteSheet(ImageLoader.loadImage(START_BUTTON));
        SpriteSheet buttonHovered = new SpriteSheet(ImageLoader.loadImage(START_BUTTON_HOVERED));
        SpriteSheet buttonAboutNormal = new SpriteSheet(ImageLoader.loadImage(ABOUT_BUTTON));
        SpriteSheet buttonAboutHovered = new SpriteSheet(ImageLoader.loadImage(ABOUT_BUTTON_HOVERED));
        SpriteSheet buttonQuitNormal = new SpriteSheet(ImageLoader.loadImage(QUIT_BUTTON));
        SpriteSheet buttonQuitHovered = new SpriteSheet(ImageLoader.loadImage(QUIT_BUTTON_HOVERED));
        SpriteSheet buttonBackNormal = new SpriteSheet(ImageLoader.loadImage(BACK_BUTTON));
        SpriteSheet buttonBackHovered = new SpriteSheet(ImageLoader.loadImage(BACK_BUTTON_HOVERED));
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(PATH));
        SpriteSheet playerSheet =  new SpriteSheet(ImageLoader.loadImage(HERO_PATH));
        SpriteSheet trees = new SpriteSheet(ImageLoader.loadImage(TREES_PATH));
        SpriteSheet villains = new SpriteSheet(ImageLoader.loadImage(VILLAIN_PATH));
        SpriteSheet chaserVillains = new SpriteSheet(ImageLoader.loadImage(CHASER_VILLAIN_PATH));
        SpriteSheet end = new SpriteSheet(ImageLoader.loadImage(END_GAME));
        SpriteSheet playerHealthSheet = new SpriteSheet(ImageLoader.loadImage(PlAYER_HEALTH));
        SpriteSheet inventorySheet = new SpriteSheet(ImageLoader.loadImage(INVENTORY));
        SpriteSheet coinSheet = new SpriteSheet(ImageLoader.loadImage(COIN));
        SpriteSheet stone1Sheet = new SpriteSheet(ImageLoader.loadImage(STONE1));
        SpriteSheet rockItemSheet = new SpriteSheet(ImageLoader.loadImage(ROCKITEM));
        SpriteSheet meatSheet = new SpriteSheet(ImageLoader.loadImage(MEAT));
        SpriteSheet sheep = new SpriteSheet(ImageLoader.loadImage(SHEEP));

//        player = hero.crop(0,0, 50, 50);
        startScreen = new BufferedImage[1];
        startScreen[0] = screen.crop(0, 0, 999, 556);
        infoTeam = new BufferedImage[1];
        infoTeam[0] = info.crop(0,0,864,540);
        btn_start = new BufferedImage[3];
        btn_start[0] = buttonNormal.crop(0, 0, 412, 107);
        btn_start[1] = buttonHovered.crop(0, 0, 412, 107);
        btn_about = new BufferedImage[2];
        btn_about[0] = buttonAboutNormal.crop(0, 0, 231, 91);
        btn_about[1] = buttonAboutHovered.crop(0, 0, 231, 91);
        btn_quit = new BufferedImage[2];
        btn_quit[0] = buttonQuitNormal.crop(0, 0, 159, 107);
        btn_quit[1] = buttonQuitHovered.crop(0, 0, 159, 107);
        btn_back = new BufferedImage[2];
        btn_back[0] = buttonBackNormal.crop(0, 0, 198, 91);
        btn_back[1] = buttonBackHovered.crop(0, 0, 198, 91);
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

        //player health icon
        playerHealth = playerHealthSheet.crop(0,0,24,24);

        chaserVillain_Left = new BufferedImage[2];
        chaserVillain_Right = new BufferedImage[2];
        chaserVillain_Up = new BufferedImage[2];
        chaserVillain_Down = new BufferedImage[2];
        chaserVillain_Left[0] = chaserVillains.crop(7, 64, 59, 64);
        chaserVillain_Left[1] = chaserVillains.crop(134, 64, 59, 64);
        chaserVillain_Right[0] = chaserVillains.crop(7, 128, 59, 64);
        chaserVillain_Right[1] = chaserVillains.crop(134, 128, 59, 64);
        chaserVillain_Up[0] = chaserVillains.crop(4, 193, 59, 64);
        chaserVillain_Up[1] = chaserVillains.crop(132, 193, 59, 64);
        chaserVillain_Down[0] = chaserVillains.crop(4, 0, 59, 64);
        chaserVillain_Down[1] = chaserVillains.crop(132, 0, 59, 64);
        chaserVillains_DownStill = chaserVillains.crop(4, 0, 59, 64);
        chaserVillains_UpStill = chaserVillains.crop(4, 193, 59, 64);
        chaserVillains_LeftStill = chaserVillains.crop(7, 64, 59, 64);
        chaserVillains_RightStill = chaserVillains.crop(7, 128, 59, 64);

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
        villains_DownStill = villains.crop(4, 0, 59, 64);
        villains_UpStill = villains.crop(4, 193, 59, 64);
        villains_LeftStill = villains.crop(7, 64, 59, 64);
        villains_RightStill = villains.crop(7, 128, 59, 64);

        sheep_Down = new BufferedImage[2];
        sheep_Up = new BufferedImage[2];
        sheep_Left = new BufferedImage[2];
        sheep_Right = new BufferedImage[2];
        sheep_Down[0] = sheep.crop(0, 114, 60, 57);
        sheep_Down[1] = sheep.crop(120, 114, 60, 57);
        sheep_DownStill = sheep.crop(60, 114, 60, 57);
        sheep_Up[0] = sheep.crop(0,0,60, 57);
        sheep_Up[1] = sheep.crop(120,0,60, 57);
        sheep_UpStill = sheep.crop(60,0,60, 57);
        sheep_Left[0] = sheep.crop(0, 57, 60, 57);
        sheep_Left[1] = sheep.crop(120, 57, 60, 57);
        sheep_LeftStiil = sheep.crop(60, 57, 60, 57);
        sheep_Right[0] = sheep.crop(0, 171, 60, 57);
        sheep_Right[1] = sheep.crop(120, 171, 60, 57);
        sheep_RightStill = sheep.crop(60, 171, 60, 57);

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
        cutDownTree = trees.crop(69, 385, 57, 56);
        coin = coinSheet.crop(0,0,30, 30);
        rock1 = stone1Sheet.crop(0,0,30, 60);
        rockItem = rockItemSheet.crop(0,0,30, 30);
        meat = meatSheet.crop(0,0,42,42);

        inventory = inventorySheet.crop(0,0, 600,50);

        endGame = end.crop(0,0, 178, 178);
    }
}
