package entity;

import game.Game;
import game.Handler;

import java.awt.*;

//ENTITY ABSTRACT CLASS (ENEMY, PLAYER, ITEMS ETC)
public abstract class Entity {
    private final int DEFAULT_HEALTH = 100;
    protected int height, width;
    protected float x, y;
    protected Handler handler;
    private boolean isActive = true;

    protected int health;
    //COLLISION RECTANGLE
    protected Rectangle bounds;

    public Entity(Handler handler, float x, float y, int width, int height) {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = DEFAULT_HEALTH;

        bounds = new Rectangle(0, 0, width, height);
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract void die();

    public void recieveDamage(int amount) {
        System.out.println("getting hurt" + amount + "  remaining health" + health);
        health -= amount;
        if(health <= 0) {
            isActive = false;
            die();
        }
    }

    public boolean checkEntityCollisions(float xOffset, float yOffset){
        for(Entity e : handler.getWorld().getEntityManager().getEntities()){
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset){
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getHealth() {
        return health;
    }

}
