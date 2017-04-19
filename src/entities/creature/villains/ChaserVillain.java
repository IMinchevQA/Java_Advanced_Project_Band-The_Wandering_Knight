package entities.creature.villains;

import entities.creature.Player;
import game.Handler;
import items.Item;

import java.awt.*;
import java.util.Random;

public class ChaserVillain extends Villain{

    private static final int DAMAGE_HEALTH_POINTS = 3;

    public ChaserVillain(Handler handler, float x, float y) {
        super(handler, x, y, getDefaultCreatureWidth(), getDefaultCreatureHeight());
        setDamageHealthPoints(DAMAGE_HEALTH_POINTS);
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

    private void chaseTimeChecker() {
        if (System.nanoTime() % super.getNanotimeDivisor() == 0) {
            chase();
        }
    }

    private void chase() {
        super.setxMove(super.getInitialChaseMoveXYValues()[0]);
        super.setyMove(super.getInitialChaseMoveXYValues()[1]);
        Player player = super.getHandler().getWorld().getEntityManager().getPlayer();

        if (Math.abs(super.getX() - player.getX()) < super.getChaseActivationRange() && Math.abs(super.getY() - player.getY()) < super.getChaseActivationRange()) {
            if (super.getX() < player.getX()) {
                super.setxMove(super.getxMove() +1);
            }
            if (super.getX() > player.getX()) {
                super.setxMove(super.getxMove() -1);
            }
            if (super.getY() < player.getY()) {
                super.setyMove(super.getyMove() +1);
            }
            if (super.getY() > player.getY()) {
                super.setyMove(super.getyMove() -1);
            }
        } else {
            Random rand = new Random();
            super.setxMove(rand.nextInt(3) - 1);
            super.setyMove(rand.nextInt(3) - 1);
        }
    }
}
