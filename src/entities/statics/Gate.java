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
        super.getBoundsRect().setBounds(0, 0, 25, 15);
    }

    @Override
    public void tick() {
        checkIntersect();
    }

    public void checkIntersect(){
        Player player = super.getHandler().getWorld().getEntityManager().getPlayer();
        if (player.getInventory().getKey() >= 1){
            isGateOpened = true;
//            bounds.width = -13;
//            bounds.height = -13;
            super.getBoundsRect().setBounds((int)super.getBoundsRect().getX(), (int)super.getBoundsRect().getY(), -13, -13);
        } else {
            isGateOpened = false;
//            bounds.width = 25;
//            bounds.height = 15;
            super.getBoundsRect().setBounds((int)super.getBoundsRect().getX(), (int)super.getBoundsRect().getY(), 25, 15);
        }
    }

   @Override
   public void die(){

   }

    @Override
    public void render(Graphics g) {
       if(isGateOpened){
           g.drawImage(Assets.getFieldElement("gateOpened"), (int) (super.getX() - super.getHandler().getGameCamera().getxOffset()), (int) (super.getY() - super.getHandler().getGameCamera
                   ().getyOffset()), super.getWidth(), super.getHeight(), null);
       }else{
           g.drawImage(Assets.getFieldElement("gateClosed"), (int) (super.getX() - super.getHandler().getGameCamera().getxOffset()), (int) (super.getY() - super.getHandler().getGameCamera
                   ().getyOffset()), super.getWidth(), super.getHeight(), null);
       }
    }
}
