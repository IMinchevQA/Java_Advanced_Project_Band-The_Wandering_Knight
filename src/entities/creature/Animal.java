package entities.creature;


import game.Handler;
import gfx.Animation;
import gfx.Assets;
import items.Item;
import items.ItemFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Animal extends Creature{

    private static final int ANIMATION_SPEED = 500;

    private Animation animLeft, animRight, animUp, animDown;
    private String lastMovedDirection = "Down";

    public Animal(Handler handler, float x, float y) {
        super(handler, x, y, getDefaultCreatureWidth(), getDefaultCreatureHeight());
        this.animLeft = new Animation(ANIMATION_SPEED, Assets.getEntitiesMotionPositions("sheep_Left"));
        this.animRight = new Animation(ANIMATION_SPEED, Assets.getEntitiesMotionPositions("sheep_Right"));
        this.animUp = new Animation(ANIMATION_SPEED, Assets.getEntitiesMotionPositions("sheep_Up"));
        this.animDown = new Animation(ANIMATION_SPEED, Assets.getEntitiesMotionPositions("sheep_Down"));
        super.getBoundsRect().setBounds(30, 30, 30, 30);
    }

    @Override
    public void tick() {
        this.animLeft.tick();
        this.animRight.tick();
        this.animUp.tick();
        this.animDown.tick();

        time();
        move();
    }
    private void time(){
        Random random = new Random();
        if(System.nanoTime() % (random.nextInt(super.getRandomDivisorBound()) + super.getDirectionChangeDelay()) == 0){
            moveSheep();
        }
    }

    private void moveSheep(){
        Random rand = new Random();
        this.setxMove(rand.nextInt(super.getRandomMoveStepGenerationBound()) - super.getDefaultSpeed());
        this.setyMove(rand.nextInt(super.getRandomMoveStepGenerationBound()) - super.getDefaultSpeed());
        if(rand.nextInt(super.getRandomMoveStepGenerationBound()) == 0){
            this.setxMove(0);
            this.setyMove(0);
        }
    }
    private BufferedImage getCurrentAnimationFrame() {
        if (this.getxMove() < 0) {
            this.lastMovedDirection = "Left";
            return this.animLeft.getCurrentFrame();
        } else if (this.getxMove() > 0) {
            this.lastMovedDirection = "Right";
            return this.animRight.getCurrentFrame();
        } else if (this.getyMove() < 0) {
            this.lastMovedDirection = "Up";
            return this.animUp.getCurrentFrame();
        } else if (this.getyMove()> 0) {
            this.lastMovedDirection = "Down";
            return this.animDown.getCurrentFrame();
        } else {
            switch (this.lastMovedDirection) {
                case "Down":
                    return Assets.getEntitiesStillPositions("sheep_DownStill");
                case "Left":
                    return Assets.getEntitiesStillPositions("sheep_LeftStiil");
                case "Right":
                    return Assets.getEntitiesStillPositions( "sheep_RightStill");
                default:
                    return Assets.getEntitiesStillPositions("sheep_UpStill");
            }
        }

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

}
