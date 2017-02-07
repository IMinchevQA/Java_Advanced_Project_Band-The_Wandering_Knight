package entity.staticEntity;

import game.Handler;
import gfx.Assets;
import tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {
    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

        bounds.x = 20;
        bounds.y = 37;
        bounds.width = 25;
        bounds.height = 23;
    }

    @Override
    public void tick() {

    }

    @Override
    public void die() {
        //TODO HANDLE AFTER DEATH

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree,(int)(x- handler.getGameCamera().getxOffset()),
                (int)(y - handler.getGameCamera().getyOffset()), width, height, null);
//                g.setColor(Color.red);
//        g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),
//                (int) (y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
    }
}
