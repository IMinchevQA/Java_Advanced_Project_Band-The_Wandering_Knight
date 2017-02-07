package entity.staticEntity;

import entity.Entity;
import game.Handler;


public abstract class StaticEntity extends Entity {
    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }
}
