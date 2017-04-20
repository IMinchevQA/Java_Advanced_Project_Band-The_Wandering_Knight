package entities.creature.villains;

import entities.creature.Creature;
import game.Handler;
import gfx.Animation;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Villain extends Creature {

    private static final int ATTACK_RANGE = 10;
    private static final int NANOTIME_DIVISOR = 25;
    private static final int ATTACK_COOLDOWN = 600;
    private static final int ATTACK_AREA_SIZE = 45;
    private static final int[] INITIAL_COLLISION_OFFSETS =  {0, 0};
    private static final int[] INITIAL_CHASE_MOVE_X_Y_VALUES = {0, 0};
    private static final int CHASE_ACTIVATION_RANGE = 150;
    private static final int DEFAULT_VILLAIN_SPEED = 500;

    private int damageHealthPoints;
    private Animation animLeft, animRight, animUp, animDown;
    private String lastMovedDirection = "Down";
    private long lastAttackTimer, attackTimer;

    Villain(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);

        //Animations
        if (this instanceof ChaserVillain) {
            this.animLeft = new Animation(DEFAULT_VILLAIN_SPEED, Assets.getEntitiesMotionPositions("chaserVillain_Left"));
            this.animRight = new Animation(DEFAULT_VILLAIN_SPEED, Assets.getEntitiesMotionPositions("chaserVillain_Right"));
            this.animUp = new Animation(DEFAULT_VILLAIN_SPEED, Assets.getEntitiesMotionPositions("chaserVillain_Up"));
            this.animDown = new Animation(DEFAULT_VILLAIN_SPEED, Assets.getEntitiesMotionPositions("chaserVillain_Down"));
        } else {
            this.animLeft = new Animation(DEFAULT_VILLAIN_SPEED, Assets.getEntitiesMotionPositions("villain_Left"));
            this.animRight = new Animation(DEFAULT_VILLAIN_SPEED, Assets.getEntitiesMotionPositions("villain_Right"));
            this.animUp = new Animation(DEFAULT_VILLAIN_SPEED, Assets.getEntitiesMotionPositions("villain_Up"));
            this.animDown = new Animation(DEFAULT_VILLAIN_SPEED, Assets.getEntitiesMotionPositions("villain_Down"));
        }
    }

    @Override
    public void tick() {
        this.animLeft.tick();
        this.animRight.tick();
        this.animUp.tick();
        this.animDown.tick();
    }

    void checkAttacks() {
        this.attackTimer += System.currentTimeMillis() - this.lastAttackTimer;
        this.lastAttackTimer = System.currentTimeMillis();
        if (this.attackTimer < this.getAttackCoolDown()) {
            return;
        }

        Rectangle collisionBounds = getCollisionBounds(this.getInitialCollisionOffsets()[0], this.getInitialCollisionOffsets()[1]);
        Rectangle attackBounds = new Rectangle();

        attackBounds.width = this.getAttackAreaSize();
        attackBounds.height = this.getAttackAreaSize();

        attackBounds.x = collisionBounds.x - this.getAttackRange();
        attackBounds.y = collisionBounds.y - this.getAttackRange();

        this.attackTimer = 0;

        if (super.getHandler().getWorld().getEntityManager().getPlayer().getCollisionBounds(this.getInitialCollisionOffsets()[0], this.getInitialCollisionOffsets()[1]).intersects(attackBounds)) {
            super.getHandler().getWorld().getEntityManager().getPlayer().hurt(damageHealthPoints);
        }
    }

    private int getAttackRange() {
        return ATTACK_RANGE;
    }

    private int getAttackCoolDown() {
        return ATTACK_COOLDOWN;
    }

    private int[] getInitialCollisionOffsets() {
        return INITIAL_COLLISION_OFFSETS;
    }

    private int getAttackAreaSize() {
        return ATTACK_AREA_SIZE;
    }

    protected void setDamageHealthPoints(int damageHealthPoints) {
        this.damageHealthPoints = damageHealthPoints;
    }

    protected int getNanotimeDivisor() {
        return NANOTIME_DIVISOR;
    }

    protected int[] getInitialChaseMoveXYValues() {
        return INITIAL_CHASE_MOVE_X_Y_VALUES;
    }

    protected int getChaseActivationRange() {
        return CHASE_ACTIVATION_RANGE;
    }


    BufferedImage getCurrentAnimationFrame() {
        if (super.getxMove() < 0) {
            this.lastMovedDirection = "Left";
            return this.animLeft.getCurrentFrame();
        } else if (super.getxMove() > 0) {
            this.lastMovedDirection = "Right";
            return this.animRight.getCurrentFrame();
        } else if (super.getyMove() < 0) {
            this.lastMovedDirection = "Up";
            return this.animUp.getCurrentFrame();
        } else if (super.getyMove() > 0) {
            this.lastMovedDirection = "Down";
            return this.animDown.getCurrentFrame();
        } else {
            if (this.getClass().getSimpleName().equals("RandomVillain")) {
                switch (this.lastMovedDirection) {
                    case "Down":
                        return Assets.getEntitiesStillPositions("villains_DownStill");
                    case "Left":
                        return Assets.getEntitiesStillPositions("villains_LeftStill");
                    case "Right":
                        return Assets.getEntitiesStillPositions("villains_RightStill");
                    default:
                        return Assets.getEntitiesStillPositions("villains_UpStill");
                }
            } else {
                switch (this.lastMovedDirection) {
                    case "Down":
                        return Assets.getEntitiesStillPositions("chaserVillains_DownStill");
                    case "Left":
                        return Assets.getEntitiesStillPositions( "chaserVillains_LeftStill");
                    case "Right":
                        return Assets.getEntitiesStillPositions("chaserVillains_RightStill");
                    default:
                        return Assets.getEntitiesStillPositions("chaserVillains_UpStill");
                }
            }
        }
    }
}
