package entities.creature.villains;

import entities.creature.Creature;
import game.Handler;
import items.Item;

import java.awt.*;
import java.util.Random;

public class RandomVillain extends Villain{

    private static final int DAMAGE_HEALTH_POINTS = 1;
    private long lastAttackTimer, attackTimer;

    public RandomVillain(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
    }

    @Override
    public void tick() {
        moveTimeChecker();
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
        super.getHandler().getWorld().getItemManager().addItem(Item.meatItem.createNew((int) super.getX(), (int) super.getY()));
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

        if(super.getHandler().getWorld().getEntityManager().getPlayer().getCollisionBounds(super.getInitialCollisionOffsets()[0],super.getInitialCollisionOffsets()[1]).intersects(attackBounds)){
            super.getHandler().getWorld().getEntityManager().getPlayer().hurt(DAMAGE_HEALTH_POINTS);
        }
    }

    private void moveTimeChecker(){
        Random random = new Random();
        if (System.nanoTime() % (random.nextInt(50) + 30 ) == 0){
            moveVillain();
        }
    }

    private void moveVillain(){
        Random rand = new Random();
        super.xMove = rand.nextInt(3) - 1;
        super.yMove = rand.nextInt(3) - 1;
        if(rand.nextInt(3) == 0){
            super.xMove = 0;
            super.yMove = 0;
        }
    }
}
