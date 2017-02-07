package entity;

//ABSTRACT CREATURE IN CASE WE ADD ITEMS AND OTHER NON CREATURE ENTITIES
import entity.Entity;
import game.Game;
import game.Handler;
import tiles.Tile;

public abstract class Creature extends Entity {

    //CREATURE VARIABLES GO HERE HEALTH DAMAGE ARMOR ETC ETC
    public static final int DEFAULT_HEALTH = 10;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_CREATURE_WIDTH = 64,
            DEFAULT_CREATURE_HEIGHT = 64;

    protected int health;
    protected float speed;
    protected float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        health = DEFAULT_HEALTH;
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    public void move() {
        if (!checkEntityCollisions(xMove, 0f))
            moveX();
        if (!checkEntityCollisions(0f, yMove))
            moveY();
    }

    public void moveX() {
        if (xMove > 0) { //MOVE RIGHT
            //X COORDINATE OF THE TILE PLAYER TRIES TO MOVE INTO
            //RIGHT LINE OF THE BOUNDS RECTANGLE
            int tx = (int) (x + bounds.x + xMove + bounds.width) / Tile.TILE_WIDTH;
            //IF THE TILE WE TRY TO MOVE INTO IS NOT SOLID
            //CHECKING THE UPPER RIGHT && LOWER RIGHT CORNER OF THE BOUNDS SQUARE INSIDE
            //OUR PLAYER
            if (collisionWithTile(tx, (int) (bounds.y + y) / Tile.TILE_HEIGHT) &&
                    collisionWithTile(tx, (int) (bounds.y + y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            } else {
                x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
            }

        } else if (xMove < 0) { //MOVE LEFT
            //LEFT LINE OF THE OF BOUNDS RECTANGLE
            int tx = (int) (x + bounds.x + xMove) / Tile.TILE_WIDTH;
            if (collisionWithTile(tx, (int) (bounds.y + y) / Tile.TILE_HEIGHT) &&
                    collisionWithTile(tx, (int) (bounds.y + y + bounds.height) / Tile.TILE_HEIGHT)) {
                x += xMove;
            }
        }
    }

    public void moveY() {
        if (yMove > 0) { //MOVE DOWN
            //LOWER LINE OF BOUNDS RECTANGLE
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

            if (collisionWithTile((int) (x + bounds.x + xMove) / Tile.TILE_WIDTH, ty) &&
                    collisionWithTile((int) (x + bounds.x + xMove + bounds.width) / Tile.TILE_WIDTH, ty)) {
                y += yMove;
            }

        } else if (yMove < 0) { //MOVE UP
            //UPPER LINE OF BOUNDS RECTANGLE
            int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;

            if (collisionWithTile((int) (x + bounds.x + xMove + bounds.width) / Tile.TILE_WIDTH, ty) &&
                    collisionWithTile((int) (x + bounds.x + xMove) / Tile.TILE_HEIGHT, ty)) {
                y += yMove;
            }

        }
    }

    //TAKE COORDINATE AND RETURN IS WALKABLE, RETURNS TRUE IF CAN WALK OVER IT
    protected boolean collisionWithTile(int x, int y) {
        return handler.getWorld().getTile(x, y).isWalkable();
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
