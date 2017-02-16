package entities.creature.villains;

import entities.creature.Creature;
import game.Handler;
import gfx.Animation;
import items.Item;

import java.awt.*;
import java.util.Random;

public class RandomVillain extends Villain{

    //public static int villainSpeed = 2;
    private Animation animLeft, animRight, animUp, animDown;
    private long lastAttackTimer, attackCooldown = 600, attackTimer = attackCooldown;

    public RandomVillain(Handler handler, float x, float y) {
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
        handler.getWorld().getItemManager().addItem(Item.meatItem.createNew((int) x, (int) y));
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

        at.x = cb.x -10;
        at.y = cb.y -10;

        attackTimer = 0;

        if(handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0,0).intersects(at)){
            handler.getWorld().getEntityManager().getPlayer().hurt(1);
        }
    }

    public void timeChecker(){
        Random random = new Random();
        if(System.nanoTime() % (random.nextInt(50) + 30 )== 0){
            moveVillain();
        }
    }

    public void moveVillain(){
        Random rand = new Random();
        xMove = rand.nextInt(3) - 1;
        yMove = rand.nextInt(3) - 1;
        if(rand.nextInt(3) == 0){
            xMove = 0;
            yMove = 0;
        }
    }
}
