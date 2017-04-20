package gfx;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

//ALL PRELOADED OBJECTS
public class Assets {
    //FILE SIZE DIVIDED BY TILES PER ROW/ HEIGHT (SPRITE.PNG)
    private static final int TILE_WIDTH = 500/16 -2, TILE_HEIGHT = 330/12;
    private static final int TREE_WIDTH = 63, TREE_HEIGHT = 97;

    //IMAGE PATHS
    private static final String PATH = "./res/GameField/TileSamples-Uncut/sprite.png";
    private static final String HERO_PATH = "./res/Hero/heroechico_swd_AllPositions.png";
    private static final String HERO_ARMOR_PATH = "./res/Hero/WithArmor/ArmoredHero.png";
    private static final String TREES_PATH = "./res/GameField/TileSamples-Uncut/Trees.png";
    private static final String START_SCREEN = "./res/Buttons/StartScreen.png";
    private static final String INFO_TEAM = "./res/Buttons/aboutINFO.png";
    private static final String START_BUTTON = "./res/Buttons/startGame.png";
    private static final String START_BUTTON_HOVERED = "./res/Buttons/startGameRed.png";
    private static final String LOAD_BUTTON = "./res/Buttons/loadGame.png";
    private static final String LOAD_BUTTON_HOVERED = "./res/Buttons/loadGameRed.png";
    private static final String ABOUT_BUTTON = "./res/Buttons/aboutBlack.png";
    private static final String ABOUT_BUTTON_HOVERED = "./res/Buttons/aboutRed.png";
    private static final String QUIT_BUTTON = "./res/Buttons/quitBlack.png";
    private static final String QUIT_BUTTON_HOVERED = "./res/Buttons/quitRed.png";
    private static final String BACK_BUTTON = "./res/Buttons/blackBack.png";
    private static final String BACK_BUTTON_HOVERED = "./res/Buttons/backRed.png";
    private static final String VILLAIN_PATH = "./res/Monster/Monster_uncut.png";
    private static final String CHASER_VILLAIN_PATH = "./res/Monster/Monster_uncut_Chaser.png";
    private static final String END_GAME = "./res/game-over-png-20.png";
    private static final String PlAYER_HEALTH = "./res/Hero/heart.png";
    private static final String INVENTORY = "./res/GameField/inventory.png";
    private static final String COIN = "./res/GameField/coin.png";
    private static final String STONE1 = "./res/GameField/stone1.png";
    private static final String ROCKITEM = "./res/GameField/rockItem.png";
    private static final String MEAT = "./res/GameField/meat.png";
    private static final String SHEEP = "./res/Sheep/sheep_walk.png";
    private static final String CHEST = "./res/GameField/chest.png";//AY
    private static final String KEY = "./res/GameField/key.png";//AY
    private static final String GATE_CLOSED = "./res/GameField/gate/gateClosed.png";//AY
    private static final String GATE_OPENED = "./res/GameField/gate/gateOpened.png";//AY
    //iceland
    private static final String SNOW = "./res/GameField/iceland/snow.png";//AY
    private static final String SNOWY_TREE = "./res/GameField/iceland/SnowyTree.png";//AY
    private static final String SNOWY_BUSH = "./res/GameField/iceland/SnowyBush.png";//AY
    //desert
    private static final String SAND = "./res/GameField/desert/sand.png";//AY
    private static final String CACTUS1 = "./res/GameField/desert/cactus1.png";//AY
    private static final String CACTUS2 = "./res/GameField/desert/cactus2.png";//AY
    private static final String PALMTREE = "./res/GameField/desert/palmtree.png";//AY
    private static final String MAGIC = "./res/Magic/magic.png";
    private static final String SOUND = "./res/SoundButtons/sound.png";
    private static final String SOUND_MUTE = "./res/SoundButtons/sound_mute.png";

    private static Map<String, BufferedImage> gameFieldElements = new HashMap<>();
    private static Map<String, BufferedImage[]> menuElements = new HashMap<>();
    private static Map<String, BufferedImage[]> playerMotionPositions = new HashMap<>();
    private static Map<String, BufferedImage> playerStillPositions = new HashMap<>();
    private static Map<String, BufferedImage[]> entitiesMotionPositions = new HashMap<>();
    private static Map<String, BufferedImage> entitiesStillPositions = new HashMap<>();


    public static void init() {
        SpriteSheet screen = new SpriteSheet(ImageLoader.loadImage(START_SCREEN));
        SpriteSheet info = new SpriteSheet(ImageLoader.loadImage(INFO_TEAM));
        SpriteSheet buttonNormal = new SpriteSheet(ImageLoader.loadImage(START_BUTTON));
        SpriteSheet buttonHovered = new SpriteSheet(ImageLoader.loadImage(START_BUTTON_HOVERED));
        SpriteSheet buttonLoadNormal = new SpriteSheet(ImageLoader.loadImage(LOAD_BUTTON));
        SpriteSheet buttonLoadHovered = new SpriteSheet(ImageLoader.loadImage(LOAD_BUTTON_HOVERED));
        SpriteSheet buttonAboutNormal = new SpriteSheet(ImageLoader.loadImage(ABOUT_BUTTON));
        SpriteSheet buttonAboutHovered = new SpriteSheet(ImageLoader.loadImage(ABOUT_BUTTON_HOVERED));
        SpriteSheet buttonQuitNormal = new SpriteSheet(ImageLoader.loadImage(QUIT_BUTTON));
        SpriteSheet buttonQuitHovered = new SpriteSheet(ImageLoader.loadImage(QUIT_BUTTON_HOVERED));
        SpriteSheet buttonBackNormal = new SpriteSheet(ImageLoader.loadImage(BACK_BUTTON));
        SpriteSheet buttonBackHovered = new SpriteSheet(ImageLoader.loadImage(BACK_BUTTON_HOVERED));
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(PATH));
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage(HERO_PATH));
        SpriteSheet playerArmoredSheet = new SpriteSheet(ImageLoader.loadImage(HERO_ARMOR_PATH));
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
        SpriteSheet chestSheet = new SpriteSheet(ImageLoader.loadImage(CHEST));//AY
        SpriteSheet keySheet = new SpriteSheet(ImageLoader.loadImage(KEY));//AY
        SpriteSheet gateClosedSheet = new SpriteSheet(ImageLoader.loadImage(GATE_CLOSED));//AY
        SpriteSheet gateOpenedSheet = new SpriteSheet(ImageLoader.loadImage(GATE_OPENED));//AY
        //iceland
        SpriteSheet snowSheet = new SpriteSheet(ImageLoader.loadImage(SNOW));//AY
        SpriteSheet snowyTreeSheet = new SpriteSheet(ImageLoader.loadImage(SNOWY_TREE));//AY
        SpriteSheet snowyBushSheet = new SpriteSheet(ImageLoader.loadImage(SNOWY_BUSH));//AY
        //desert
        SpriteSheet sandSheet = new SpriteSheet(ImageLoader.loadImage(SAND));//AY
        SpriteSheet cactus1Sheet = new SpriteSheet(ImageLoader.loadImage(CACTUS1));//AY
        SpriteSheet cactus2Sheet = new SpriteSheet(ImageLoader.loadImage(CACTUS2));//AY
        SpriteSheet palmtreeSheet = new SpriteSheet(ImageLoader.loadImage(PALMTREE));//AY
        SpriteSheet magicSheet = new SpriteSheet(ImageLoader.loadImage(MAGIC));
        SpriteSheet soundSheet = new SpriteSheet(ImageLoader.loadImage(SOUND));
        SpriteSheet soundMuteSheet = new SpriteSheet(ImageLoader.loadImage(SOUND_MUTE));

        //Entities still positions
        //Villains
        entitiesStillPositions.put("villains_LeftFighting", villains.crop(70, 64, 59, 64));
        entitiesStillPositions.put("villains_RightFighting", villains.crop(70, 128, 59, 64));
        entitiesStillPositions.put("villains_UpFighting", villains.crop(69, 193, 59, 64));
        entitiesStillPositions.put("villains_DownFighting", villains.crop(69, 0, 59, 64));
        entitiesStillPositions.put("villains_LeftStill", villains.crop(7, 64, 59, 64));
        entitiesStillPositions.put("villains_RightStill", villains.crop(7, 128, 59, 64));
        entitiesStillPositions.put("villains_UpStill", villains.crop(4, 193, 59, 64));
        entitiesStillPositions.put("villains_DownStill", villains.crop(4, 0, 59, 64));
        entitiesStillPositions.put("chaserVillains_LeftStill", chaserVillains.crop(7, 64, 59, 64));
        entitiesStillPositions.put("chaserVillains_RightStill", chaserVillains.crop(7, 128, 59, 64));
        entitiesStillPositions.put("chaserVillains_UpStill", chaserVillains.crop(4, 193, 59, 64));
        entitiesStillPositions.put("chaserVillains_DownStill", chaserVillains.crop(4, 0, 59, 64));
        //Sheeps
        entitiesStillPositions.put("sheep_LeftStiil", sheep.crop(60, 57, 60, 57));
        entitiesStillPositions.put("sheep_RightStill", sheep.crop(60, 171, 60, 57));
        entitiesStillPositions.put("sheep_DownStill", sheep.crop(60, 114, 60, 57));
        entitiesStillPositions.put("sheep_UpStill", sheep.crop(60, 0, 60, 57));


        //Entities moving positions
        //Villains
        entitiesMotionPositions.put("villain_Left", new BufferedImage[]{ villains.crop(7, 64, 59, 64), villains.crop(134, 64, 59, 64) });
        entitiesMotionPositions.put("villain_Right", new BufferedImage[]{ villains.crop(7, 128, 59, 64), villains.crop(134, 128, 59, 64) });
        entitiesMotionPositions.put("villain_Up", new BufferedImage[]{ villains.crop(4, 193, 59, 64), villains.crop(132, 193, 59, 64) });
        entitiesMotionPositions.put("villain_Down", new BufferedImage[]{ villains.crop(4, 0, 59, 64), villains.crop(132, 0, 59, 64) });
        entitiesMotionPositions.put("chaserVillain_Left", new BufferedImage[]{ chaserVillains.crop(7, 64, 59, 64), chaserVillains.crop(134, 64, 59, 64) });
        entitiesMotionPositions.put("chaserVillain_Right", new BufferedImage[]{ chaserVillains.crop(7, 128, 59, 64), chaserVillains.crop(134, 128, 59, 64) });
        entitiesMotionPositions.put("chaserVillain_Up", new BufferedImage[]{ chaserVillains.crop(4, 193, 59, 64), chaserVillains.crop(132, 193, 59, 64) });
        entitiesMotionPositions.put("chaserVillain_Down", new BufferedImage[]{ chaserVillains.crop(4, 0, 59, 64), chaserVillains.crop(132, 0, 59, 64) });

        //Sheep
        entitiesMotionPositions.put("sheep_Left", new BufferedImage[]{ sheep.crop(0, 57, 60, 57), sheep.crop(120, 57, 60, 57) });
        entitiesMotionPositions.put("sheep_Right", new BufferedImage[]{ sheep.crop(0, 171, 60, 57), sheep.crop(120, 171, 60, 57) });
        entitiesMotionPositions.put("sheep_Up", new BufferedImage[]{ sheep.crop(0, 0, 60, 57), sheep.crop(120, 0, 60, 57) });
        entitiesMotionPositions.put("sheep_Down", new BufferedImage[]{ sheep.crop(0, 114, 60, 57), sheep.crop(120, 114, 60, 57) });

        //Player still positions - no armor
        playerStillPositions.put ("player_LeftStill", playerSheet.crop(315, 150, 50, 50));
        playerStillPositions.put ("player_RightStill", playerSheet.crop(330, 250, 50, 50));
        playerStillPositions.put ("player_UpStill", playerSheet.crop(324, 350, 53, 50));
        playerStillPositions.put ("player_DownStill", playerSheet.crop(20, 50, 45, 50));

        //Player still positions - armored
        playerStillPositions.put ("playerArmored_LeftStill", playerArmoredSheet.crop(52, 98, 40, 35));
        playerStillPositions.put ("playerArmored_RightStill", playerArmoredSheet.crop(52, 146, 40, 35));
        playerStillPositions.put ("playerArmored_UpStill", playerArmoredSheet.crop(52, 52, 40, 35));
        playerStillPositions.put ("playerArmored_DownStill", playerArmoredSheet.crop(52, 4, 40, 35));


        //Player motion positions - no armor
        playerMotionPositions.put("player_Left", new BufferedImage[]{ playerSheet.crop(28, 144, 45, 55), playerSheet.crop(222, 150, 45, 50)});
        playerMotionPositions.put("player_Right", new BufferedImage[]{ playerSheet.crop(20, 245, 45, 55), playerSheet.crop(229, 252, 45, 50)});
        playerMotionPositions.put("player_Up", new BufferedImage[]{ playerSheet.crop(26, 350, 45, 49), playerSheet.crop(116, 348, 50, 49)});
        playerMotionPositions.put("player_Down", new BufferedImage[]{ playerSheet.crop(120, 50, 45, 50), playerSheet.crop(211, 50, 50, 50)});
        playerMotionPositions.put("player_LeftAttack", new BufferedImage[]{ playerSheet.crop(28, 144, 45, 55), playerSheet.crop(222, 150, 45, 50), playerSheet.crop(320, 150, 45, 50)});
        playerMotionPositions.put("player_RightAttack", new BufferedImage[]{ playerSheet.crop(20, 245, 45, 55), playerSheet.crop(229, 252, 45, 50), playerSheet.crop(335, 252, 45, 50)});
        playerMotionPositions.put("player_UpAttack", new BufferedImage[]{ playerSheet.crop(315, 350, 65, 50), playerSheet.crop(225, 350, 65, 50), playerSheet.crop(115, 348, 70, 50) });
        playerMotionPositions.put("player_DownAttack", new BufferedImage[]{ playerSheet.crop(211, 50, 50, 50), playerSheet.crop(120, 50, 45, 50), playerSheet.crop(20, 50, 45, 50) });

        //Player motion positions - armored
        playerMotionPositions.put("playerArmored_Left", new BufferedImage[]{ playerArmoredSheet.crop(7, 98, 35, 35), playerArmoredSheet.crop(104, 98, 35, 35) });
        playerMotionPositions.put("playerArmored_Right", new BufferedImage[]{ playerArmoredSheet.crop(4, 147, 35, 35), playerArmoredSheet.crop(100, 147, 35, 35) });
        playerMotionPositions.put("playerArmored_Up", new BufferedImage[]{ playerArmoredSheet.crop(7, 52, 35, 35), playerArmoredSheet.crop(102, 52, 35, 35) });
        playerMotionPositions.put("playerArmored_Down", new BufferedImage[]{ playerArmoredSheet.crop(7, 4, 35, 35), playerArmoredSheet.crop(102, 4, 35, 35) });
        playerMotionPositions.put("playerArmored_LeftAttack", new BufferedImage[]{ playerArmoredSheet.crop(291, 98, 35, 35), playerArmoredSheet.crop(237, 98, 38, 35), playerArmoredSheet.crop(196, 98, 35, 35) });
        playerMotionPositions.put("playerArmored_RightAttack", new BufferedImage[]{ playerArmoredSheet.crop(297, 148, 35, 35), playerArmoredSheet.crop(254, 148, 35, 35), playerArmoredSheet.crop(200, 148, 35, 35) });
        playerMotionPositions.put("playerArmored_UpAttack", new BufferedImage[]{ playerArmoredSheet.crop(295, 46, 35, 40), playerArmoredSheet.crop(246, 46, 35, 40), playerArmoredSheet.crop(195, 46, 35, 40) });
        playerMotionPositions.put("playerArmored_DownAttack", new BufferedImage[]{ playerArmoredSheet.crop(336, 0, 35, 40), playerArmoredSheet.crop(287, 0, 35, 40), playerArmoredSheet.crop(248, 0, 35, 40) });

        //Menu elements
        menuElements.put("btn_start", new BufferedImage[]{ buttonNormal.crop(0, 0, 412, 107), buttonHovered.crop(0, 0, 412, 107)});
        menuElements.put("btn_load", new BufferedImage[]{ buttonLoadNormal.crop(0, 0, 412, 107), buttonLoadHovered.crop(0, 0, 412, 107)});
        menuElements.put("btn_about", new BufferedImage[]{ buttonAboutNormal.crop(0, 0, 231, 91), buttonAboutHovered.crop(0, 0, 231, 91)});
        menuElements.put("btn_quit", new BufferedImage[]{ buttonQuitNormal.crop(0, 0, 159, 107), buttonQuitHovered.crop(0, 0, 159, 107)});
        menuElements.put("btn_back", new BufferedImage[]{ buttonBackNormal.crop(0, 0, 198, 91), buttonBackHovered.crop(0, 0, 198, 91)});
        menuElements.put("startScreen", new BufferedImage[]{ screen.crop(0, 0, 999, 556) });
        menuElements.put("infoTeam", new BufferedImage[]{ info.crop(0, 0, 864, 540) });
        menuElements.put("sound", new BufferedImage[]{ soundSheet.crop(0, 0, 26, 26), soundMuteSheet.crop(0, 0, 26, 26) });
        menuElements.put("soundAlt", new BufferedImage[]{ soundSheet.crop(0, 0, 26, 26), soundMuteSheet.crop(0, 0, 26, 26) });

        //ALL GAME FIELD TILES AND ELEMENTS
        gameFieldElements.put("grass", sheet.crop(3 * TILE_WIDTH, 2 * TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT));
        gameFieldElements.put("water", sheet.crop(0, 0, TILE_WIDTH, TILE_HEIGHT));
        gameFieldElements.put("stone", sheet.crop(141, 45, TILE_WIDTH, TILE_HEIGHT));
        gameFieldElements.put("bush1", sheet.crop(150, 430, TILE_WIDTH + 5, TILE_HEIGHT + 5));
        gameFieldElements.put("bush2", sheet.crop(150, 535, TILE_WIDTH + 5, TILE_HEIGHT + 5));
        gameFieldElements.put("bush3", sheet.crop(270, 430, TILE_WIDTH + 5, TILE_HEIGHT + 5));
        gameFieldElements.put("w_lilly", sheet.crop(15, 395, TILE_WIDTH + 10, TILE_HEIGHT + 10));
        gameFieldElements.put("w_stone", sheet.crop(15, 525, TILE_WIDTH + 5, TILE_HEIGHT + 5));
        gameFieldElements.put("mushroom1", sheet.crop(85, 438, TILE_WIDTH + 5, TILE_HEIGHT + 5));
        gameFieldElements.put("mushroom2", sheet.crop(78, 530, TILE_WIDTH + 10, TILE_HEIGHT + 10));
        gameFieldElements.put("flower1", sheet.crop(210, 430, TILE_WIDTH + 10, TILE_HEIGHT + 10));
        gameFieldElements.put("flower2", sheet.crop(395, 520, TILE_WIDTH + 5, TILE_HEIGHT + 5));
        gameFieldElements.put("well_full", sheet.crop(210, 525, TILE_WIDTH + 5, TILE_HEIGHT + 5));
        gameFieldElements.put("stone_wall_up1", sheet.crop(438, 505 - TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT));
        gameFieldElements.put("stone_wall_up2", sheet.crop(435 + TILE_WIDTH, 505 - TILE_HEIGHT, TILE_WIDTH, TILE_HEIGHT));
        gameFieldElements.put("stone_wall_down1", sheet.crop(445, 450, TILE_WIDTH, TILE_HEIGHT));
        gameFieldElements.put("stone_wall_down2", sheet.crop(445 + TILE_WIDTH, 450, TILE_WIDTH, TILE_HEIGHT));
        gameFieldElements.put("stone_wall_left",  sheet.crop(510 - TILE_WIDTH, 398, TILE_WIDTH - 5, TILE_HEIGHT - 5));
        gameFieldElements.put("stone_wall_right", sheet.crop(500, 400, TILE_WIDTH - 5, TILE_HEIGHT - 5));
        gameFieldElements.put("tree1", trees.crop(0, 0, TREE_WIDTH, TREE_HEIGHT));
        gameFieldElements.put("tree2", trees.crop(TILE_WIDTH, 0, TREE_WIDTH, TREE_HEIGHT));
        gameFieldElements.put("tree3", trees.crop(TREE_WIDTH * 2, 0, TREE_WIDTH, TREE_HEIGHT));
        gameFieldElements.put("forest", trees.crop(385, 290, 120, 220));
        gameFieldElements.put("cutDownTree", trees.crop(69, 385, 57, 56));
        gameFieldElements.put("snow", snowSheet.crop(0, 0, 59, 59));
        gameFieldElements.put("snowyTree", snowyTreeSheet.crop(0, 0, 38, 60));
        gameFieldElements.put("snowyBush", snowyBushSheet.crop(0, 0, 59, 59));
        gameFieldElements.put("sand", sandSheet.crop(0, 0, 59, 59));
        gameFieldElements.put("cactus1", cactus1Sheet.crop(0, 0, 59, 59));
        gameFieldElements.put("cactus2", cactus2Sheet.crop(0, 0, 59, 59));
        gameFieldElements.put("palmTreeSheet", palmtreeSheet.crop(0, 0, 120, 151));
        gameFieldElements.put("coin", coinSheet.crop(0, 0, 30, 30));
        gameFieldElements.put("rock1", stone1Sheet.crop(0, 0, 30, 60));
        gameFieldElements.put("rockItem", rockItemSheet.crop(0, 0, 30, 30));
        gameFieldElements.put("meat", meatSheet.crop(0, 0, 42, 42));
        gameFieldElements.put("chest", chestSheet.crop(0, 0, 66, 66));
        gameFieldElements.put("key", keySheet.crop(0, 0, 30, 30));
        gameFieldElements.put("gateClosed", gateClosedSheet.crop(0, 0, 60, 60));
        gameFieldElements.put("gateOpened", gateOpenedSheet.crop(0, 0, 58, 60));
        gameFieldElements.put("magic", magicSheet.crop(0, 0, 26, 24));
        gameFieldElements.put("inventory", inventorySheet.crop(0, 0, 600, 50));
        gameFieldElements.put("playerHealth", playerHealthSheet.crop(0, 0, 24, 24));
        gameFieldElements.put("endGame", end.crop(0, 0, 178, 178));
    }

    public static BufferedImage getFieldElement(String tileName) {
        return gameFieldElements.get(tileName);
    }

    public static BufferedImage[] getMenuElement(String menuElementName) {
        //Return cloned menuElement in order to avoid values modification from outside
        return Stream.of(menuElements.get(menuElementName)).toArray(BufferedImage[]::new);
    }

    public static BufferedImage[] getPlayerMotionPositions(String motionPositionName) {
        return Stream.of(playerMotionPositions.get(motionPositionName)).toArray(BufferedImage[]::new);
    }

    public static BufferedImage getPlayerStillPositions(String stillPositionName) {
        return playerStillPositions.get(stillPositionName);
    }

    public static BufferedImage[] getEntitiesMotionPositions(String motionPositionName) {
        return Stream.of(entitiesMotionPositions.get(motionPositionName)).toArray(BufferedImage[]::new);
    }

    public static BufferedImage getEntitiesStillPositions(String stillPositionName) {
        return entitiesStillPositions.get(stillPositionName);
    }
}


