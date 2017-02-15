package entities.statics;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;

public class Rock extends StaticEntity{

    public static final int STONE_WIDTH = 30, STONE_HEIGHT = 60;

    public Rock(Handler handler, float x, float y){
        super(handler, x,y, STONE_WIDTH, STONE_HEIGHT);

        //bounding box
        bounds.x = 10;
        bounds.y = (int) (height/1.5f);
        bounds.width = width-20;
        bounds.height = (int) (height - height/1.5f);

    }

    @Override
    public void tick() {

    }
    @Override
    public void die(){
        handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rock1, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera
                ().getyOffset()), width, height, null);
    }

}
