package entities.creature;

//ABSTRACT CREATURE IN CASE WE ADD ITEMS AND OTHER NON CREATURE ENTITIES
import entities.EntityImpl;
import game.Handler;
import tiles.Tile;

public abstract class Creature extends EntityImpl {

    //CREATURE VARIABLES GO HERE HEALTH DAMAGE ARMOR ETC ETC

    private static final float DEFAULT_SPEED = 3.0f;
    private static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

    private float speed;
    private float xMove, yMove;

    public Creature(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
        speed = DEFAULT_SPEED;
        xMove = 0;
        yMove = 0;
    }

    protected void move(){
        if (!checkEntityCollisions(xMove, 0f)) {
            moveX();
        }
        if (!checkEntityCollisions(0f, yMove)) {
            moveY();
        }
    }

    private void moveX(){
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

    private void moveY(){
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


    private boolean collisionWithTile(int x, int y) {
        return super.getHandler().getWorld().getTile(x, y).isSolid();
    }

    public int getHealth() {
        return super.getHealth();
    }

    public void setHealth(int health) {
        super.setHealth(health);
    }

    protected static int getDefaultCreatureWidth() {
        return DEFAULT_CREATURE_WIDTH;
    }

    protected static int getDefaultCreatureHeight() {
        return DEFAULT_CREATURE_HEIGHT;
    }

    protected float getxMove() {
        return xMove;
    }

    protected float getyMove() {
        return yMove;
    }

    protected void setxMove(float xMove) {
        this.xMove = xMove;
    }

    protected void setyMove(float yMove) {
        this.yMove = yMove;
    }
}
