package entity;

import game.Handler;
import gfx.Assets;

import java.awt.*;

public class Tree extends StaticEntity {

    private static final int TREE_WIDTH = 60, TREE_HEIGHT = 100;

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, TREE_WIDTH, TREE_HEIGHT);
        
        //need to be fixed
        bounds.x = 32;
        bounds.y = 48;
        bounds.width = width - 20;
        bounds.height = (int) (height - height / 1.5f);
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
