package entities.creature;

//ABSTRACT CREATURE IN CASE WE ADD ITEMS AND OTHER NON CREATURE ENTITIES
import entities.Entity;
import game.Handler;
import tiles.Tile;

public abstract class Creature extends Entity {

    //CREATURE VARIABLES GO HERE HEALTH DAMAGE ARMOR ETC ETC

    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
            DEFAULT_CREATURE_HEIGHT = 64;

    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);

        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move(){
        if (!checkEntityCollisions(xMove, 0f)) {
            moveX();
        }
        if (!checkEntityCollisions(0f, yMove)) {
            moveY();
        }
    }

    public void moveX(){
        if(xMove > 0) {//Move right
            //Tile (Index in the world matrix)WHERE THE HERO/CREATURE IS POSITIONED AT MOMENT
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.getTileWidth();
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.getTileHeight()) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.getTileHeight())){
                x += xMove;
            } else {
                x = tx * Tile.getTileWidth() - bounds.x - bounds.width -1;
            }

        } else if(xMove < 0) { //Move left
            //Tile (Index in the world matrix)WHERE THE HERO/CREATURE IS POSITIONED AT MOMENT
            int tx = (int) (x + xMove + bounds.x) / Tile.getTileWidth();
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.getTileHeight()) &&
                    !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.getTileHeight())){
                x += xMove;
            } else {
                x = tx * Tile.getTileWidth() + Tile.getTileWidth() - bounds.x;
            }
        }
    }

    public void moveY(){
        if(yMove < 0) { //Move up
            int ty = (int) (y + yMove + bounds.y) / Tile.getTileHeight();
            if(!collisionWithTile((int)(x + bounds.x) / Tile.getTileWidth(), ty) &&
                    !collisionWithTile((int)(x + bounds.x + bounds.width) / Tile.getTileWidth(), ty)) {
                y += yMove;
            } else {
                y = ty * Tile.getTileHeight() + Tile.getTileHeight() - bounds.y;
            }
        } else if (yMove > 0) { //Move down
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.getTileHeight();
            if(!collisionWithTile((int)(x + bounds.x) / Tile.getTileWidth(), ty) &&
                    !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.getTileWidth(), ty)){
                y += yMove;
            } else {
                y = ty * Tile.getTileHeight() - bounds.y - bounds.height - 1;
            }
        }
    }

    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isSolid();
    }

    //GETTERS & SETTERS

    public float getxMove() {
        return xMove;
    }

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

}
