package entities.statics;

import game.Handler;
import gfx.Assets;

import java.awt.*;

//AY
public class Gate extends StaticEntity {

    private static final int GATE_WIDTH = 60, GATE_HEIGHT = 60;
    //private static boolean isGateOpened=false;

    public Gate(Handler handler, float x, float y) {
        super(handler, x, y, GATE_WIDTH, GATE_HEIGHT);

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 25;
        bounds.height = 15;
    }

    @Override
    public void tick() {

    }



   @Override
   public void die(){

   }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.gateClosed, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera
                ().getyOffset()), width, height, null);
    }
}
