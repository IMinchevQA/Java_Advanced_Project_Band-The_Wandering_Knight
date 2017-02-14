package entities.creature.villains;

import entities.creature.Creature;
import entities.creature.Player;
import game.Handler;
import gfx.Animation;
import gfx.Assets;
import items.Item;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by Home on 2/14/2017.
 */
public class ChaserVillan extends Creature {

    //public static int villainSpeed = 2;
    private Animation animLeft, animRight, animUp, animDown;
    private String lastMovedDirection = "Down";
    private long lastAttackTimer, attackCooldown = 600, attackTimer = attackCooldown;


    public ChaserVillan(Handler handler, float x, float y) {
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

        animLeft.tick();
        animRight.tick();
        animUp.tick();
        animDown.tick();
        timeChecker();
        move();

        checkAttacks();

    }

    @Override
    public void render(Graphics g) {
//        TODO: temporally render until movement is implemented
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera
                ().getyOffset()), width, height, null);
    }

    @Override
    public void die() {
        handler.getWorld().getItemManager().addItem(Item.coinItem.createNew((int) x, (int) y));

    }

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        }
        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle at = new Rectangle();
        int arSize = 45;
        at.width = arSize;
        at.height = arSize;

        at.x = cb.x - 10;
        at.y = cb.y - 10;

        attackTimer = 0;

        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(at)) {
            handler.getWorld().getEntityManager().getPlayer().hurt(1);
        }
    }

    private void timeChecker() {
        if (System.nanoTime() % 25 == 0) {
            chase();
        }
    }

    private void chase() {
        xMove = 0;
        yMove = 0;
        Player player = handler.getWorld().getEntityManager().getPlayer();

        if (Math.abs(x - player.getX()) < 150 || Math.abs(y - player.getY()) < 150) {
            if (x < player.getX()) {
                xMove++;
            }
            if (x > player.getX()) {
                xMove--;
            }
            if (y < player.getY()) {
                yMove++;
            }
            if (y > player.getY()) {
                yMove--;
            }
        } else {
            Random rand = new Random();
            xMove = rand.nextInt(3) - 1;
            yMove = rand.nextInt(3) - 1;


        }


    }

    private BufferedImage getCurrentAnimationFrame() {
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
                    return Assets.villains_DownStill;
                case "Left":
                    return Assets.villains_LeftStill;
                case "Right":
                    return Assets.villains_RightStill;
                default:
                    return Assets.villains_UpStill;
            }
        }

    }
}
