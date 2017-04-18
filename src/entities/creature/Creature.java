package entities.creature;

//ABSTRACT CREATURE IN CASE WE ADD ITEMS AND OTHER NON CREATURE ENTITIES
import entities.EntityImpl;
import game.Handler;
import tiles.Tile;

public abstract class Creature extends EntityImpl {

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
        if (this.xMove > 0) {//Move right
            //Tile (Index in the world matrix)WHERE THE HERO/CREATURE IS POSITIONED AT MOMENT
            int tx = (int) (super.getX() + this.xMove + super.getBoundsRect().getX() + super.getBoundsRect().getWidth()) / Tile.getTileWidth();
            if (!collisionWithTile(tx, (int) (super.getY() + super.getBoundsRect().getY()) / Tile.getTileHeight()) &&
                    !collisionWithTile(tx, (int) (super.getY() + super.getBoundsRect().getY() + super.getBoundsRect().getHeight()) / Tile.getTileHeight())) {
                super.setX(super.getX() + this.xMove);
            } else {
                super.setX((float)(tx * Tile.getTileWidth() - super.getBoundsRect().getX() - super.getBoundsRect().getWidth() -1));
            }

        } else if (this.xMove < 0) { //Move left
            //Tile (Index in the world matrix)WHERE THE HERO/CREATURE IS POSITIONED AT MOMENT
            int tx = (int) (super.getX() + this.xMove + super.getBoundsRect().getX()) / Tile.getTileWidth();
            if (!collisionWithTile(tx, (int) (super.getY() + super.getBoundsRect().getY()) / Tile.getTileHeight()) &&
                    !collisionWithTile(tx, (int) (super.getY() + super.getBoundsRect().getY() + super.getBoundsRect().getHeight()) / Tile.getTileHeight())) {
                super.setX(super.getX() + this.xMove);
            } else {
                super.setX((float)(tx * Tile.getTileWidth() + Tile.getTileWidth() - this.getBoundsRect().getX()));
            }
        }
    }

    public void moveY(){
        if(this.yMove < 0) { //Move up
            int ty = (int) (super.getY() + this.yMove + super.getBoundsRect().getY()) / Tile.getTileHeight();
            if(!collisionWithTile((int)(super.getX() + super.getBoundsRect().getX()) / Tile.getTileWidth(), ty) &&
                    !collisionWithTile((int)(super.getX() + super.getBoundsRect().getX() + super.getBoundsRect().getWidth()) / Tile.getTileWidth(), ty)) {
                super.setY(super.getY() + this.yMove);
            } else {
                super.setY((float)(ty * Tile.getTileHeight() + Tile.getTileHeight() - super.getBoundsRect().getY()));
            }
        } else if (this.yMove > 0) { //Move down
            int ty = (int) (super.getY() + this.yMove + super.getBoundsRect().getY() + super.getBoundsRect().getHeight()) / Tile.getTileHeight();
            if(!collisionWithTile((int)(super.getX() + super.getBoundsRect().getX()) / Tile.getTileWidth(), ty) &&
                    !collisionWithTile((int) (super.getX() + super.getBoundsRect().getX() + super.getBoundsRect().getWidth()) / Tile.getTileWidth(), ty)){
                super.setY(super.getY() + this.yMove);
            } else {
                super.setY((float)(ty * Tile.getTileHeight() - super.getBoundsRect().getY() - super.getBoundsRect().getHeight() - 1));
            }
        }
    }


    protected boolean collisionWithTile(int x, int y) {
        return super.getHandler().getWorld().getTile(x, y).isSolid();
    }

    //GETTERS & SETTERS

    public int getHealth() {
        return super.getHealth();
    }

    public void setHealth(int health) {
        super.setHealth(health);
    }

}
