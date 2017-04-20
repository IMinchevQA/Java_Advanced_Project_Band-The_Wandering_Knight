package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;
import items.ItemFactory;

import java.awt.*;
//AY
public class Chest extends StaticEntity {

    private static final int TENT_WIDTH = 60, TENT_HEIGHT = 60;

    public Chest(Handler handler, float x, float y) {
        super(handler, x, y, TENT_WIDTH, TENT_HEIGHT);
        super.getBoundsRect().setBounds(0, 25, 60, 60);
    }

    @Override
    public void die(){
        super.getHandler().getWorld().getItemManager().addItem(ItemFactory.createNew("key", (int) super.getX(), (int) super.getY(), super.getHandler()));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.getFieldElement("chest"), (int) (super.getX() - super.getHandler().getGameCamera().getxOffset()), (int) (super.getY() - super.getHandler().getGameCamera
                ().getyOffset()), super.getWidth(), super.getHeight(), null);
    }
}
