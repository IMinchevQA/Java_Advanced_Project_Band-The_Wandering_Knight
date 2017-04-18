package entities.creature.villains;

import entities.creature.Creature;
import entities.creature.Player;
import game.Handler;
import items.Item;

import java.awt.*;
import java.util.Random;

public class ChaserVillain extends Villain{

    private static final int DAMAGE_HEALTH_POINTS = 3;
    private long lastAttackTimer, attackTimer;

    public ChaserVillain(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
    }

    @Override
    public void tick() {
        chaseTimeChecker();
        move();

        checkAttacks();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (super.getX() - super.getHandler().getGameCamera().getxOffset()), (int) (super.getY() - super.getHandler().getGameCamera
                ().getyOffset()), super.getWidth(), super.getHeight(), null);
    }

    @Override
    public void die() {
        super.getHandler().getWorld().getItemManager().addItem(Item.coinItem.createNew((int) super.getX(), (int) super.getY()));

    }

    private void checkAttacks() {
        this.attackTimer += System.currentTimeMillis() - this.lastAttackTimer;
        this.lastAttackTimer = System.currentTimeMillis();
        if (this.attackTimer < super.getAttackCoolDown()) {
            return;
        }

        Rectangle collisionBounds = getCollisionBounds(super.getInitialCollisionOffsets()[0], super.getInitialCollisionOffsets()[1]);
        Rectangle attackBounds = new Rectangle();

        attackBounds.width = super.getAttackAreaSize();
        attackBounds.height = super.getAttackAreaSize();

        attackBounds.x = collisionBounds.x - super.getAttackRange();
        attackBounds.y = collisionBounds.y - super.getAttackRange();

        this.attackTimer = 0;

        if (super.getHandler().getWorld().getEntityManager().getPlayer().getCollisionBounds(super.getInitialCollisionOffsets()[0], super.getInitialCollisionOffsets()[1]).intersects(attackBounds)) {
            super.getHandler().getWorld().getEntityManager().getPlayer().hurt(DAMAGE_HEALTH_POINTS);
        }
    }

    private void chaseTimeChecker() {
        if (System.nanoTime() % super.getNanotimeDivisor() == 0) {
            chase();
        }
    }

    private void chase() {
        super.xMove = super.getInitialChaseMoveXYValues()[0];
        super.yMove = super.getInitialChaseMoveXYValues()[1];
        Player player = super.getHandler().getWorld().getEntityManager().getPlayer();

        if (Math.abs(super.getX() - player.getX()) < super.getChaseActivationRange() && Math.abs(super.getY() - player.getY()) < super.getChaseActivationRange()) {
            if (super.getX() < player.getX()) {
                super.xMove++;
            }
            if (super.getX() > player.getX()) {
                super.xMove--;
            }
            if (super.getY() < player.getY()) {
                super.yMove++;
            }
            if (super.getY() > player.getY()) {
                super.yMove--;
            }
        } else {
            Random rand = new Random();
            super.xMove = rand.nextInt(3) - 1;
            super.yMove = rand.nextInt(3) - 1;
        }
    }
}
