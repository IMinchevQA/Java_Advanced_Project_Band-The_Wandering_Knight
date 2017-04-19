package entities;

import game.Handler;

import java.awt.*;

//ENTITY ABSTRACT CLASS (ENEMY, PLAYER, ITEMS ETC)
public abstract class EntityImpl implements Entity {

    private static final int DEFAULT_HEALTH = 3;
    private Handler handler;
    private float x, y;
    private int width, height;
    private int health;
    private boolean active = true;
    private Rectangle boundsRect;

    public EntityImpl(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = DEFAULT_HEALTH;
        this.boundsRect = new Rectangle(32, 24, 24, 32);

    }

    public void hurt(int amt){
        this.health -= amt;
        if(this.health <= 0){
            this.active = false;
            die();
        }
    }

    protected boolean checkEntityCollisions(float xOffset, float yOffset) {
        for (EntityImpl entityImpl : this.handler.getWorld().getEntityManager().getEntities()) {
            if (entityImpl.equals(this)) {
                continue;
            }
            if (entityImpl.getCollisionBounds(0f, 0f).intersects(this.getCollisionBounds(xOffset, yOffset))) {
                return true;
            }

        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (this.x + this.boundsRect.x + xOffset), (int) (this.y + this.boundsRect.y + yOffset), this.boundsRect.width, this.boundsRect.height);
    }

    public void tick(){
//  If someone can fix this, get a kiss from me. I lost a lot of time trying.
    }

    public void die(){
//  Same story. Interface segregation principle is so difficult here.
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Handler getHandler() {
        return this.handler;
    }

    boolean isActive() {
        return this.active;
    }

    protected void setActive(boolean active) {
        this.active = active;
    }

    public Rectangle getBoundsRect() {
        return this.boundsRect;
    }
}
