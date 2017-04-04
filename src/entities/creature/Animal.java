package entities.creature;


import game.Handler;
import gfx.Animation;
import gfx.Assets;
import items.Item;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Home on 2/17/2017.
 */
public class Animal extends Creature{
    private Animation animLeft, animRight, animUp, animDown;
    private String lastMovedDirection = "Down";

    public Animal(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        animLeft = new Animation(500, Assets.getEntitiesMotionPositions("sheep_Left"));
        animRight = new Animation(500, Assets.getEntitiesMotionPositions("sheep_Right"));
        animUp = new Animation(500, Assets.getEntitiesMotionPositions("sheep_Up"));
        animDown = new Animation(500, Assets.getEntitiesMotionPositions("sheep_Down"));

       bounds.x = 30;
       bounds.y = 30;
       bounds.width = 30;
       bounds.height = 30;
    }

    @Override
    public void tick() {
        animLeft.tick();
        animRight.tick();
        animUp.tick();
        animDown.tick();

        time();
        move();
    }
    private void time(){
        Random random = new Random();
        if(System.nanoTime() % (random.nextInt(50) + 30 )== 0){
            moveSheep();
        }
    }

    private void moveSheep(){
        Random rand = new Random();
        xMove = rand.nextInt(3) - 1;
        yMove = rand.nextInt(3) - 1;
        if(rand.nextInt(3) == 0){
            xMove = 0;
            yMove = 0;
        }
    }
    public BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            lastMovedDirection = "Left";
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            lastMovedDirection = "Right";
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            lastMovedDirection = "Up";
            return animUp.getCurrentFrame();
        } else if (yMove > 0) {
            lastMovedDirection = "Down";
            return animDown.getCurrentFrame();
        } else {
            switch (lastMovedDirection) {
                case "Down":
                    return Assets.getEntitiesStillPositions("sheep_DownStill");
                case "Left":
                    return Assets.getEntitiesStillPositions("sheep_LeftStiil");
                case "Right":
                    return Assets.getEntitiesStillPositions( "sheep_RightStill");
                default:
                    return Assets.getEntitiesStillPositions("sheep_UpStill");
            }
        }

    }
    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera
                ().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.meatItem.createNew((int) x, (int) y));

    }

}
