package world;

import entities.EntityManager;
import entities.creature.Animal;
import entities.creature.Player;
import entities.creature.projectile.Projectile;
import entities.creature.villains.ChaserVillain;
import entities.creature.villains.RandomVillain;

import entities.statics.*;
import game.Handler;
import items.ItemManager;
import saves.SaveObject;
import tiles.Tile;
import utils.Loader;

import java.awt.*;
import java.util.Random;

//SEE RES/WORLD/WORLD.TXT FOR MORE INFO
public class World {

    private static final int FRAMES_ELAPSED_FOR_TO_GENERATE_NEW_ANIMAL = 5400;
    private static final int FRAMES_ELAPSED_TO_GENERATE_NEW_CHASER_VILLAIN = 3600;
    private static final int ANIMAL_XY_POSITION_RAND_INT_GENERATOR = 100;
    private static final int[] ANIMAL_XY_POSITION_COORD_ADDEND = {1000, 300};
    private static final int[] VILLAINS_XY_POSITION_RAND_INT_GENERATORS = {500, 300};
    private static final int[] VILLAINS_XY_POSITION_COORD_ADDENDS = {200, 150};

    //WORLD WIDTH AND HEIGHT, HERO SPAWN COORDINATES WILL BE INITIALIZED IN LOAD WORLD METHOD FROM OUR WORLD FILE
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tilesWorldMatrix;
    private EntityManager entityManager;
    private ItemManager itemManager;
    private long time = 0;

    public World(Handler handler, String path) {
        this.handler = handler;
        this.entityManager = new EntityManager(new Player(handler, 30, 30));
        this.itemManager = new ItemManager(handler);
        //THE path PARAMETER IS PASSED BY GameState.java LINE Nr. -19!!!
        loadWorld(path);

        createEntities(handler);

        this.entityManager.getPlayer().setX(this.spawnX);
        this.entityManager.getPlayer().setY(this.spawnY);
    }

    public void tick() {
        this.time++;
        this.itemManager.tick();
        this.entityManager.tick();
        if (this.time % FRAMES_ELAPSED_FOR_TO_GENERATE_NEW_ANIMAL == 0){
            Random rd = new Random();
            entityManager.addEntity(new Animal(handler, rd.nextInt(ANIMAL_XY_POSITION_RAND_INT_GENERATOR) + ANIMAL_XY_POSITION_COORD_ADDEND[0], rd.nextInt(ANIMAL_XY_POSITION_RAND_INT_GENERATOR) + ANIMAL_XY_POSITION_COORD_ADDEND[1]));
        }

        if(time % FRAMES_ELAPSED_TO_GENERATE_NEW_CHASER_VILLAIN == 0){
            Random random = new Random();
            entityManager.addEntity(new ChaserVillain(handler, random.nextInt(VILLAINS_XY_POSITION_RAND_INT_GENERATORS[0]) + VILLAINS_XY_POSITION_COORD_ADDENDS[0], random.nextInt(VILLAINS_XY_POSITION_RAND_INT_GENERATORS[1]) + VILLAINS_XY_POSITION_COORD_ADDENDS[1]));
            entityManager.addEntity(new RandomVillain(handler, random.nextInt(VILLAINS_XY_POSITION_RAND_INT_GENERATORS[0]) + VILLAINS_XY_POSITION_COORD_ADDENDS[0], random.nextInt(VILLAINS_XY_POSITION_RAND_INT_GENERATORS[1]) + VILLAINS_XY_POSITION_COORD_ADDENDS[1]));
            this.time = 0;
        }
        if(this.entityManager.getPlayer().isShooting() && this.time % 10 == 0
                && entityManager.getPlayer().getMana() > 5){
            entityManager.addEntity(new Projectile(handler,
                    entityManager.getPlayer().getX(),
                    entityManager.getPlayer().getY(),
                    entityManager.getPlayer().getDir()));
            entityManager.getPlayer().takeMana();
        }


    }

    public void render(Graphics g) {
        //xStart AND yStart CONTAIN THE Most-Top-Left Tile THAT THE USER CAN CURRENTLY SEE ON THE SCREEN.
        //xEnd AND yEnd CONTAIN THE Most-Bottom-Right Tile THAT THE USER CAN CURRENTLY SEE ON THE SCREEN.
        //THE PURPOSE IS TO render ONLY TILES VISIBLE ON DISPLAY.
        int xStart = (int) Math.max(0, this.handler.getGameCamera().getxOffset() / Tile.getTileWidth());
        int xEnd = (int) Math.min(this.width, (this.handler.getGameCamera().getxOffset() + this.handler.getWidth()) / Tile.getTileWidth()+ 1);
        int yStart = (int) Math.max(0, this.handler.getGameCamera().getyOffset() / Tile.getTileHeight());
        int yEnd = (int) Math.min(this.height, (this.handler.getGameCamera().getyOffset() + this.handler.getHeight()) / Tile.getTileHeight() + 1);

        //ITERATE THROUGH THE TILES ARRAY AND RENDER
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.getTileWidth()- this.handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.getTileHeight() - this.handler.getGameCamera().getyOffset()));
            }
        }

        this.itemManager.render(g);

        this.entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= this.width || y >= this.height) {
            return Tile.getGrassTile();
        }
        Tile t = Tile.getTiles()[this.tilesWorldMatrix[x][y]];
        //IF WE CALL WITH EMPTY MATRIX INDEX RETURN GRASS TILE
        if (t == null)
            return Tile.getGrassTile();
        return t;
    }

    private void loadWorld(String path) {
        String worldFile = Loader.loadFileAsString(path);
        //ONE DIMENSIONAL ARRAY WITH OUR FILE INFO
        String[] tokens = worldFile.split("\\s+");
        this.width = Loader.parseWorldFile(tokens[0]);
        this.height = Loader.parseWorldFile(tokens[1]);
        this.spawnX = Loader.parseWorldFile(tokens[2]);
        this.spawnY = Loader.parseWorldFile(tokens[3]);

        this.tilesWorldMatrix = new int[this.width][this.height];
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {

                //ASSIGNING EVERY INDEX TO THE ARRAY
                //NUMBER 4 IS ADDED BECAUSE FIRST 4 ELEMENTS VALUES ARE ASSIGNED TO - width, height, spawnX, spawnY
                tilesWorldMatrix[x][y] = Loader.parseWorldFile(tokens[(x+y*width) + 4]);
            }
        }
    }

    private void createEntities(Handler handler) {

        this.entityManager.addEntity(new Tree(handler, 100, 250));
        this.entityManager.addEntity(new Tree(handler, 500, 100));
        this.entityManager.addEntity(new Tree(handler, 150, 250));
        this.entityManager.addEntity(new Tree(handler, 1000, 59));
        this.entityManager.addEntity(new Tree(handler, 300, 400));
        this.entityManager.addEntity(new Tree(handler, 600, 300));
        this.entityManager.addEntity(new Tree(handler, 1600, 450));
        this.entityManager.addEntity(new Tree(handler, 700, 400));
        this.entityManager.addEntity(new Tree(handler, 1360, 231));

        this.entityManager.addEntity(new SnowyTree(handler, 600, 600));
        this.entityManager.addEntity(new SnowyTree(handler, 1800, 700));

        this.entityManager.addEntity(new PalmTree(handler, 200, 1250));
        this.entityManager.addEntity(new PalmTree(handler, 1100, 1350));

        this.entityManager.addEntity(new Rock(handler, 200, 200));
        this.entityManager.addEntity(new Rock(handler, 250, 250));
        this.entityManager.addEntity(new Rock(handler, 720, 600));
        this.entityManager.addEntity(new Rock(handler, 300, 500));
        this.entityManager.addEntity(new Rock(handler, 100, 50));
        this.entityManager.addEntity(new Rock(handler, 320, 50));
        this.entityManager.addEntity(new Rock(handler, 1150, 450));
        this.entityManager.addEntity(new Rock(handler, 1500, 1450));
        this.entityManager.addEntity(new Rock(handler, 200, 1650));

        this.entityManager.addEntity(new Animal(handler, 150, 350));
        this.entityManager.addEntity(new RandomVillain(handler, 75, 100));
        this.entityManager.addEntity(new RandomVillain(handler, 400, 800));
        this.entityManager.addEntity(new RandomVillain(handler, 500, 100));
        this.entityManager.addEntity(new RandomVillain(handler, 1175, 2100));
        this.entityManager.addEntity(new RandomVillain(handler, 2400, 800));
        this.entityManager.addEntity(new RandomVillain(handler, 2500, 100));

        this.entityManager.addEntity(new ChaserVillain(handler, 300, 270));
        this.entityManager.addEntity(new ChaserVillain(handler, 500, 300));
        this.entityManager.addEntity(new ChaserVillain(handler, 700, 500));

        this.entityManager.addEntity(new Chest(handler, 352,320));//AY
        this.entityManager.addEntity(new Chest(handler, 416,832));//AY

        this.entityManager.addEntity(new Gate(handler, 175,550));//AY
        this.entityManager.addEntity(new Gate(handler, 1140,1090));//AY
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return this.itemManager;
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    public void savedPlayer(SaveObject saveObject){
        this.entityManager.setSavedPlayer(new Player(this.handler, 30, 30, saveObject));
    }
}
