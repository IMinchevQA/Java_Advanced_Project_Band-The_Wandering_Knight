package entities;

import game.Handler;

import java.awt.*;

//ENTITY ABSTRACT CLASS (ENEMY, PLAYER, ITEMS ETC)
public abstract class Entity {

    public static final int DEFAULT_HEALTH = 3;
    protected Handler handler;
    protected float x, y;
    protected int width, height;
    protected int health;
    protected boolean active = true;
    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = DEFAULT_HEALTH;
        this.bounds = new Rectangle(0, 0, width, height);

    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void die();

    public void hurt(int amt){
        this.health -= amt;
        if(this.health <= 0){
            this.active = false;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset) {
        for (Entity entity : this.handler.getWorld().getEntityManager().getEntities()) {
            if (entity.equals(this)) {
                continue;
            }
            if (entity.getCollisionBounds(0f, 0f).intersects(this.getCollisionBounds(xOffset, yOffset))) {
                return true;
            }

        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset) {
        return new Rectangle((int) (this.x + this.bounds.x + xOffset), (int) (this.y + this.bounds.y + yOffset), this.bounds.width, this.bounds.height);
    }
    public void removeBounds(){
        this.bounds.width = -15;
        this.bounds.height = -15;
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

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
