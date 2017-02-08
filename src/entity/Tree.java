package entity;

import game.Handler;
import gfx.Assets;
import tiles.Tile;

import java.awt.*;

public class Tree extends StaticEntity {

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
g.drawImage(Assets.tree1, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
