package entities.creature.villains;

import entities.creature.Creature;
import game.Handler;
import gfx.Animation;
import gfx.Assets;

import java.awt.*;

public class Villain extends Creature {

    public static int villainSpeed = 2;
    private Animation animLeft, animRight, animUp, animDown;
//    private long lastAttackTimer, attackCooldown = 600, attackTimer = attackCooldown;

    public Villain(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 32;
        bounds.y = 24;
        bounds.width = 24;
        bounds.height = 32;

        //Animations
        animLeft = new Animation(500, Assets.villain_Left);
        animRight = new Animation(500, Assets.villain_Right);
        animUp = new Animation(500, Assets.villain_Up);
        animDown = new Animation(500, Assets.villain_Down);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
//        TODO: temporally render until movement is implemented
        g.drawImage(Assets.villains_DownFighting, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera
                ().getyOffset()), width, height, null);
    }

    @Override
    public void die() {

    }
}
