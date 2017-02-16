package entities.creature.villains;

import entities.creature.Creature;
import game.Handler;
import gfx.Animation;
import gfx.Assets;

import java.awt.image.BufferedImage;

public abstract class Villain extends Creature {

    private Animation animLeft, animRight, animUp, animDown;
    private String lastMovedDirection = "Down";

    public Villain(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);

        //Animations
        if (this instanceof ChaserVillain) {
            animLeft = new Animation(500, Assets.chaserVillain_Left);
            animRight = new Animation(500, Assets.chaserVillain_Right);
            animUp = new Animation(500, Assets.chaserVillain_Up);
            animDown = new Animation(500, Assets.chaserVillain_Down);
        } else {
            animLeft = new Animation(500, Assets.villain_Left);
            animRight = new Animation(500, Assets.villain_Right);
            animUp = new Animation(500, Assets.villain_Up);
            animDown = new Animation(500, Assets.villain_Down);
        }
    }

    @Override
    public void tick() {
        animLeft.tick();
        animRight.tick();
        animUp.tick();
        animDown.tick();
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
            if (this instanceof RandomVillain) {
                switch (lastMovedDirection) {
                    case "Down":
                        return Assets.villains_DownStill;
                    case "Left":
                        return Assets.villains_LeftStill;
                    case "Right":
                        return Assets.villains_RightStill;
                    default:
                        return Assets.villains_UpStill;
                }
            } else {
                switch (lastMovedDirection) {
                    case "Down":
                        return Assets.chaserVillains_DownStill;
                    case "Left":
                        return Assets.chaserVillains_LeftStill;
                    case "Right":
                        return Assets.chaserVillains_RightStill;
                    default:
                        return Assets.chaserVillains_UpStill;
                }
            }
        }

    }
}
