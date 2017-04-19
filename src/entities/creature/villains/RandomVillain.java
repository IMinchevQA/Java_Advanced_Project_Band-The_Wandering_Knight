package entities.creature.villains;

import game.Handler;
import items.Item;

import java.awt.*;
import java.util.Random;

public class RandomVillain extends Villain{

    private static final int DAMAGE_HEALTH_POINTS = 1;

    public RandomVillain(Handler handler, float x, float y) {
        super(handler, x, y, getDefaultCreatureWidth(), getDefaultCreatureHeight());
        setDamageHealthPoints(DAMAGE_HEALTH_POINTS);
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

    private void moveTimeChecker(){
        Random random = new Random();
        if (System.nanoTime() % (random.nextInt(50) + 30 ) == 0){
            moveVillain();
        }
    }

    private void moveVillain(){
        Random rand = new Random();
        super.setxMove(rand.nextInt(3) - 1);
        super.setyMove(rand.nextInt(3) - 1);
        if(rand.nextInt(3) == 0){
            super.setxMove(0);
            super.setyMove(0);
        }
    }
}
