package entity;

import java.awt.*;

//ENTITY ABSTRACT CLASS (ENEMY, PLAYER, ITEMS ETC)
public abstract class Entity {
    protected float x, y;

    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
}
