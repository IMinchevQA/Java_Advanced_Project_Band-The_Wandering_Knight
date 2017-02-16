package entities.creature.villains;

import entities.creature.Creature;
import entities.creature.Player;
import game.Handler;
import gfx.Animation;
import items.Item;

import java.awt.*;
import java.util.Random;

public class ChaserVillain extends Villain{

    //public static int villainSpeed = 2;
    private Animation animLeft, animRight, animUp, animDown;
    private long lastAttackTimer, attackCooldown = 600, attackTimer = attackCooldown;

    public ChaserVillain(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

        bounds.x = 32;
        bounds.y = 24;
        bounds.width = 24;
        bounds.height = 32;

    }

    @Override
    public void tick() {
        timeChecker();
        move();

        checkAttacks();
    }

    @Override
    public void render(Graphics g) {
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
            handler.getWorld().getEntityManager().getPlayer().hurt(3);
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

        if (Math.abs(x - player.getX()) < 150 && Math.abs(y - player.getY()) < 150) {
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
}