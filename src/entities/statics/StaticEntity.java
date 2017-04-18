package entities.statics;

import entities.EntityImpl;
import game.Handler;

public abstract class StaticEntity extends EntityImpl {

    public StaticEntity(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }
}
