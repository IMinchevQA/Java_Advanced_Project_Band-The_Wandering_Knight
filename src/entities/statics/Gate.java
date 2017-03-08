package entities.statics;

import entities.creature.Player;
import game.Handler;
import gfx.Assets;

import java.awt.*;

//AY
public class Gate extends StaticEntity {

    private static final int GATE_WIDTH = 60, GATE_HEIGHT = 60;
    private static boolean isGateOpened = false;

    public Gate(Handler handler, float x, float y) {
        super(handler, x, y, GATE_WIDTH, GATE_HEIGHT);

        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 25;
        bounds.height = 15;
    }

    @Override
    public void tick() {
        checkIntersect();
    }

    public void checkIntersect(){
        Player player = handler.getWorld().getEntityManager().getPlayer();
        if (player.getInventory().getKey() >= 1){
            isGateOpened = true;
            bounds.width = -13;
            bounds.height = -13;
        }else{
            isGateOpened = false;
            bounds.width = 25;
            bounds.height = 15;
        }
    }

   @Override
   public void die(){

   }

    @Override
    public void render(Graphics g) {
       if(isGateOpened){
           g.drawImage(Assets.gateOpened, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera
                   ().getyOffset()), width, height, null);
       }else{
           g.drawImage(Assets.gateClosed, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera
                   ().getyOffset()), width, height, null);
       }
    }
}
