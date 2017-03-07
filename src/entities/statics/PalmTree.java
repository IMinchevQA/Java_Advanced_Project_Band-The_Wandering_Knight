package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class PalmTree extends StaticEntity {

    private static final int TREE_WIDTH = 60, TREE_HEIGHT = 120;

    public PalmTree(Handler handler, float x, float y) {
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
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.palmtree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera
                ().getyOffset()), width, height, null);
    }
}
