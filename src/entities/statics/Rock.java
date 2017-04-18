package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Rock extends StaticEntity{

    public static final int STONE_WIDTH = 30, STONE_HEIGHT = 60;

    public Rock(Handler handler, float x, float y){
        super(handler, x,y, STONE_WIDTH, STONE_HEIGHT);
        super.getBoundsRect().setBounds(0, 30, 25, 15);

    }

    @Override
    public void tick() {

    }
    @Override
    public void die(){
        super.getHandler().getWorld().getItemManager().addItem(Item.rockItem.createNew((int) super.getX(), (int) super.getY()));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.getFieldElement("rock1"), (int) (super.getX() - super.getHandler().getGameCamera().getxOffset()), (int) (super.getY() - super.getHandler().getGameCamera
                ().getyOffset()), super.getWidth(), super.getHeight(), null);
    }

}
