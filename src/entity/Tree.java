package entity;

import game.Handler;
import gfx.Assets;

import java.awt.*;

public class Tree extends StaticEntity {

    private static final int TREE_WIDTH = 60, TREE_HEIGHT = 120;

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, TREE_WIDTH, TREE_HEIGHT);

        bounds.x = 20;
        bounds.y = 92;
        bounds.width = 22;
        bounds.height = 1;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree3, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera
                ().getyOffset()), width, height, null);
    }
}
