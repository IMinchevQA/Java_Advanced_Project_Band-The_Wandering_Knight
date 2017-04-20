package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;
import items.ItemFactory;

import java.awt.*;

public class Tree extends StaticEntity {

    private static final int TREE_WIDTH = 60, TREE_HEIGHT = 120;

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, TREE_WIDTH, TREE_HEIGHT);

        super.getBoundsRect().setBounds(20, 92, 22, 1);
    }

    @Override
    public void die(){
        super.getHandler().getWorld().getItemManager().addItem(ItemFactory.createNew("wood", (int) super.getX(), (int) super.getY(), super.getHandler()));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.getFieldElement("tree3"), (int) (super.getX() - super.getHandler().getGameCamera().getxOffset()), (int) (super.getY() - super.getHandler().getGameCamera
                ().getyOffset()), super.getWidth(), super.getHeight(), null);
    }
}
