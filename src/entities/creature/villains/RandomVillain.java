package entities.creature.villains;

import game.Handler;
import items.Item;
import items.ItemFactory;

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
        super.getHandler().getWorld().getItemManager().addItem(ItemFactory.createNew("meat", (int) super.getX(), (int) super.getY(), super.getHandler()));
    }

    private void moveTimeChecker(){
        Random random = new Random();
        if (System.nanoTime() % (random.nextInt(super.getRandomDivisorBound()) + super.getDirectionChangeDelay()) == 0){
            moveVillain();
        }
    }

    private void moveVillain(){
        Random rand = new Random();
        super.setxMove(rand.nextInt(super.getRandomMoveStepGenerationBound()) - super.getDefaultSpeed());
        super.setyMove(rand.nextInt(super.getRandomMoveStepGenerationBound()) - super.getDefaultSpeed());
        if(rand.nextInt(super.getRandomMoveStepGenerationBound()) == 0){
            super.setxMove(0);
            super.setyMove(0);
        }
    }
}
