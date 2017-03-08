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
import tiles.Tile;
import utils.Utils;

import java.awt.*;
import java.util.Random;

//SEE RES/WORLD/WORLD.TXT FOR MORE INFO
public class World {

    //WORLD WIDTH AND HEIGHT, HERO SPAWN COORDINATES WILL BE INITIALIZED IN LOADWORLD METHOD FROM OUR WORLD FILE
    private Handler handler;
    private int width, height;
    private int spawnX, spawnY;
    private int[][] tilesWorldMatrix;
    private EntityManager entityManager;
    private ItemManager itemManager;
    private long time = 0;

    public World(Handler handler, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 30, 30));
        itemManager = new ItemManager(handler);
        //THE path PARAMETER IS PASSED BY GameState.java LINE Nr. -19!!!
        loadWorld(path);

        createEntities(handler);

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public void tick() {
        time++;
        itemManager.tick();
        entityManager.tick();
        if(time % 3600 == 0){
            Random rd = new Random();
            entityManager.addEntity(new Animal(handler, rd.nextInt(100) + 1000, rd.nextInt(40) + 100 ));
            entityManager.addEntity(new Animal(handler, rd.nextInt(100) + 1000, rd.nextInt(40) + 100 ));
        }
        if(time % 5400 == 0){
            Random random = new Random();
            entityManager.addEntity(new ChaserVillain(handler, random.nextInt(40) + 80, random.nextInt(60) + 750));
            time = 0;
        }
        if(entityManager.getPlayer().isShooting() && time % 10 ==0
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
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);

        //ITERATE THROUGH THE TILES ARRAY AND RENDER
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
                        (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
            }
        }

        itemManager.render(g);

        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        if(x < 0 || y < 0 || x >= width || y >= height) {
            return Tile.grassTile;
        }
        Tile t = Tile.tiles[tilesWorldMatrix[x][y]];
        //IF WE CALL WITH EMPTY MATRIX INDEX RETURN GRASSTILE
        if (t == null)
            return Tile.grassTile;
        return t;
    }

    private void loadWorld(String path) {
        String worldFile = Utils.loadFileAsString(path);
        //ONE DIMENSIONAL ARRAY WITH OUR FILE INFO
        String[] tokens = worldFile.split("\\s+");
        width = Utils.parseWorldFile(tokens[0]);
        height = Utils.parseWorldFile(tokens[1]);
        spawnX = Utils.parseWorldFile(tokens[2]);
        spawnY = Utils.parseWorldFile(tokens[3]);

        tilesWorldMatrix = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                //ASSIGNING EVERY INDEX TO THE ARRAY
                //NUMBER 4 IS ADDED BECAUSE FIRST 4 ELEMENTS VALUES ARE ASSIGNED TO - width, height, spawnX, spawnY
                tilesWorldMatrix[x][y] = Utils.parseWorldFile(tokens[(x+y*width) + 4]);
            }
        }
    }

    private void createEntities(Handler handler) {

        entityManager.addEntity(new Tree(handler, 100, 250));
        entityManager.addEntity(new Tree(handler, 500, 100));
        entityManager.addEntity(new Tree(handler, 150, 250));
        entityManager.addEntity(new Tree(handler, 1000, 59));
        entityManager.addEntity(new Tree(handler, 300, 400));
        entityManager.addEntity(new Tree(handler, 600, 300));
        entityManager.addEntity(new Tree(handler, 1600, 450));
        entityManager.addEntity(new Tree(handler, 700, 400));
        entityManager.addEntity(new Tree(handler, 1360, 231));

        entityManager.addEntity(new SnowyTree(handler, 600, 600));
        entityManager.addEntity(new SnowyTree(handler, 1800, 700));

        entityManager.addEntity(new PalmTree(handler, 200, 1250));
        entityManager.addEntity(new PalmTree(handler, 1100, 1350));

        entityManager.addEntity(new Rock(handler, 200, 200));
        entityManager.addEntity(new Rock(handler, 250, 250));
        entityManager.addEntity(new Rock(handler, 720, 600));
        entityManager.addEntity(new Rock(handler, 300, 500));
        entityManager.addEntity(new Rock(handler, 100, 50));
        entityManager.addEntity(new Rock(handler, 320, 50));
        entityManager.addEntity(new Rock(handler, 1150, 450));
        entityManager.addEntity(new Rock(handler, 1500, 1450));
        entityManager.addEntity(new Rock(handler, 200, 1650));

        entityManager.addEntity(new Animal(handler, 150, 350));
        entityManager.addEntity(new RandomVillain(handler, 75, 100));
        entityManager.addEntity(new RandomVillain(handler, 400, 800));
        entityManager.addEntity(new RandomVillain(handler, 500, 100));
        entityManager.addEntity(new RandomVillain(handler, 1175, 2100));
        entityManager.addEntity(new RandomVillain(handler, 2400, 800));
        entityManager.addEntity(new RandomVillain(handler, 2500, 100));

        entityManager.addEntity(new ChaserVillain(handler, 300, 270));
        entityManager.addEntity(new ChaserVillain(handler, 500, 300));
        entityManager.addEntity(new ChaserVillain(handler, 700, 500));

        entityManager.addEntity(new Chest(handler, 352,320));//AY
        entityManager.addEntity(new Chest(handler, 416,832));//AY

        entityManager.addEntity(new Gate(handler, 175,550));//AY
        entityManager.addEntity(new Gate(handler, 1140,1090));//AY
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public void setItemManager(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
