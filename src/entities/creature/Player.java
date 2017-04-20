package entities.creature;

import entities.EntityImpl;
import game.Handler;
import game.MouseManager;
import game.states.EndGame;
import game.states.State;
import gfx.Animation;
import gfx.Assets;
import inventory.Inventory;
import saves.SaveObject;

import java.awt.*;
import java.awt.image.BufferedImage;

//PLAYER CLASS, PLAYER LOGIC GOES HERE
public class Player extends Creature {

    private static final float PLAYER_RUN_SPEED = 2.0f;
    private static final int MAX_VALUE_OF_MANA = 100;
    private static final int SECONDS_DIVISOR = 60;
    private static final int MANA_RESTORE_POINTS = 2;
    private static final int DEFAULT_HEALTH = 100;
    private static final int ARMOR_COINS_NECESSARY = 1;
    private static final int TOTAL_MANA_AVAILABLE = 100;
    private static final int ANIMATION_MOVE_SPEED = 400;
    private static final int ANIMATION_ATTACK_SPEED = 100;
    private static final int ARMOR_EXTRA_HEALTH = 100;
    private static final int MANA_CONSUMPTION = 2;
    private static final int MANA_MOVE_TRACE_DEFLECTION = 20;
    private static final int ATTACK_RANGE = 20;
    private static final int DAMAGE = 1;
    private static final float PLAYER_DEFAULT_SPEED = 1.0f;
    private static final int HEART_IMAGE_SIZE = 24;
    private static final int HEART_IMAGE_OFFSET = 0;
    private static final int[] HEALTH_BAR_OFFSETS = {30, 10};
    private static final int[] MANA_BAR_OFFSETS = {30, 25};
    private static final int HEALTH_AND_MANA_BAR_THICKNESS = 10;
    private float playerSpeed = 1;

    // Animations
    private Animation animLeft, animRight, animUp, animDown, animLeftAttack, animRightAttack, animUpAttack, animDownAttack;

    //Still positions
    private static BufferedImage player_LeftStill, player_RightStill, player_UpStill, player_DownStill;

    // Attack timer
    private long lastAttackTimer, attackCooldown = 700, attackTimer = attackCooldown;
    private Inventory inventory;
    private String lastMovedDirection = "Down";
    private int totalHealth;
    private int totalMana;
    private int mana;
    private int time;
    private boolean shoot = false;
    private double dir;
    private boolean hasArmor = false;



    private MouseManager mouseManager = super.getHandler().getMouseManager();

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, getDefaultCreatureWidth(), getDefaultCreatureHeight());
        //Player MUST TAKE THE game OBJECT.
        //WHY? - TO GET ACCESS TO THE InputManager's INPUT METHODS(up, down, left, right)!
        //HOW? = BY CALLING Game CLASS METHOD - get.KeyManager().up/down/left/right
        super.setHealth(DEFAULT_HEALTH);
        this.totalHealth = super.getHealth();
        this.totalMana = TOTAL_MANA_AVAILABLE;
        this.mana = TOTAL_MANA_AVAILABLE;
        super.getBoundsRect().setBounds(32, 24, 24, 32);


        //Still positions
        player_LeftStill = Assets.getPlayerStillPositions("player_LeftStill");
        player_RightStill = Assets.getPlayerStillPositions("player_RightStill");
        player_UpStill = Assets.getPlayerStillPositions("player_UpStill");
        player_DownStill = Assets.getPlayerStillPositions("player_DownStill");

        //Animations
        this.animLeft = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("player_Left"));
        this.animRight = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("player_Right"));
        this.animUp = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("player_Up"));
        this.animDown = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("player_Down"));
        this.animLeftAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("player_LeftAttack"));
        this.animRightAttack = new Animation(ANIMATION_ATTACK_SPEED,Assets.getPlayerMotionPositions("player_RightAttack"));
        this.animDownAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("player_DownAttack"));
        this.animUpAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("player_UpAttack"));
        this.inventory = new Inventory(handler, null);
    }

    public Player(Handler handler, float x, float y, SaveObject saveObject) {
        super(handler, x, y, getDefaultCreatureWidth(), getDefaultCreatureHeight());
        //Player MUST TAKE THE game OBJECT.
        //WHY? - TO GET ACCESS TO THE InputManager's INPUT METHODS(up, down, left, right)!
        //HOW? = BY CALLING Game CLASS METHOD - get.KeyManager().up/down/left/right
        super.setHealth(saveObject.getCurrentHealth());
        this.totalHealth = saveObject.getHealth();
        this.totalMana = TOTAL_MANA_AVAILABLE;
        this.mana = TOTAL_MANA_AVAILABLE;
        super.getBoundsRect().setBounds(32, 24, 24, 32);
        this.hasArmor = saveObject.isHasArmoe();

        if(this.hasArmor){
            this.animLeft = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("playerArmored_Left"));
            this.animRight = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("playerArmored_Right"));
            this.animUp = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("playerArmored_Up"));
            this.animDown = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("playerArmored_Down"));
            this.animLeftAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("playerArmored_LeftAttack"));
            this.animRightAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("playerArmored_RightAttack"));
            this.animUpAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("playerArmored_UpAttack"));
            this.animDownAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("playerArmored_DownAttack"));
            player_LeftStill = Assets.getPlayerStillPositions("playerArmored_LeftStill");
            player_RightStill = Assets.getPlayerStillPositions("playerArmored_RightStill");
            player_UpStill = Assets.getPlayerStillPositions("playerArmored_UpStill");
            player_DownStill = Assets.getPlayerStillPositions("playerArmored_DownStill");
        }else{
            //Still positions
            player_LeftStill = Assets.getPlayerStillPositions("player_LeftStill");
            player_RightStill = Assets.getPlayerStillPositions("player_RightStill");
            player_UpStill = Assets.getPlayerStillPositions("player_UpStill");
            player_DownStill = Assets.getPlayerStillPositions("player_DownStill");

            //Animations
            this.animLeft = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("player_Left"));
            this.animRight = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("player_Right"));
            this.animUp = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("player_Up"));
            this.animDown = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("player_Down"));
            this.animLeftAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("player_LeftAttack"));
            this.animRightAttack = new Animation(ANIMATION_ATTACK_SPEED,Assets.getPlayerMotionPositions("player_RightAttack"));
            this.animDownAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("player_DownAttack"));
            this.animUpAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("player_UpAttack"));
        }
        this.inventory = new Inventory(handler, saveObject.getInventory());
    }
    private void classic_Images(){
        //Still positions
        player_LeftStill = Assets.getPlayerStillPositions("player_LeftStill");
        player_RightStill = Assets.getPlayerStillPositions("player_RightStill");
        player_UpStill = Assets.getPlayerStillPositions("player_UpStill");
        player_DownStill = Assets.getPlayerStillPositions("player_DownStill");

        //Animations
        this.animLeft = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("player_Left"));
        this.animRight = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("player_Right"));
        this.animUp = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("player_Up"));
        this.animDown = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("player_Down"));
        this.animLeftAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("player_LeftAttack"));
        this.animRightAttack = new Animation(ANIMATION_ATTACK_SPEED,Assets.getPlayerMotionPositions("player_RightAttack"));
        this.animDownAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("player_DownAttack"));
        this.animUpAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("player_UpAttack"));
        //this.inventory = new Inventory(super.getHandler());

        this.totalHealth = DEFAULT_HEALTH;
        this.hasArmor = false;
    }


    private void changeAnimations_Images() {
        this.animLeft = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("playerArmored_Left"));
        this.animRight = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("playerArmored_Right"));
        this.animUp = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("playerArmored_Up"));
        this.animDown = new Animation(ANIMATION_MOVE_SPEED, Assets.getPlayerMotionPositions("playerArmored_Down"));
        this.animLeftAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("playerArmored_LeftAttack"));
        this.animRightAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("playerArmored_RightAttack"));
        this.animUpAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("playerArmored_UpAttack"));
        this.animDownAttack = new Animation(ANIMATION_ATTACK_SPEED, Assets.getPlayerMotionPositions("playerArmored_DownAttack"));
        player_LeftStill = Assets.getPlayerStillPositions("playerArmored_LeftStill");
        player_RightStill = Assets.getPlayerStillPositions("playerArmored_RightStill");
        player_UpStill = Assets.getPlayerStillPositions("playerArmored_UpStill");
        player_DownStill = Assets.getPlayerStillPositions("playerArmored_DownStill");

        this.totalHealth += DEFAULT_HEALTH;
        super.setHealth(super.getHealth() + ARMOR_EXTRA_HEALTH);
        this.hasArmor = true;
        this.inventory.useCoin();
    }

    @Override
    public void tick() {
        //Animations - Movement
        this.animLeft.tick();
        this.animRight.tick();
        this.animUp.tick();
        this.animDown.tick();
        this.animDownAttack.tick();
        this.animUpAttack.tick();
        this.animLeftAttack.tick();
        this.animRightAttack.tick();

        getInput();
        move();
        super.getHandler().getGameCamera().centerOnEntity(this);

        checkAttacks();
        this.inventory.tick();

        updateShoot();
        this.time++;
        if(this.time % SECONDS_DIVISOR == 0){
            if(this.mana < MAX_VALUE_OF_MANA){
                this.mana += MANA_RESTORE_POINTS;
            }
            time = 0;
        }
        if(this.inventory.getCoins() >= ARMOR_COINS_NECESSARY && !hasArmor){
            changeAnimations_Images();
        }

        if(super.getHealth() < DEFAULT_HEALTH && this.hasArmor){
            classic_Images();
        }

    }

    public boolean isShooting() {
        return shoot;
    }

    public double getDir() {
        return dir;
    }

    public int getMana() {
        return mana;
    }

    public void takeMana() {
        this.mana = this.getMana() - MANA_CONSUMPTION;
    }

    private void updateShoot(){
        double dx = this.mouseManager.getMouseX() - (((int)super.getX() + MANA_MOVE_TRACE_DEFLECTION)- super.getHandler().getGameCamera().getxOffset());
        double dy = this.mouseManager.getMouseY() - (((int)super.getY() + MANA_MOVE_TRACE_DEFLECTION)- super.getHandler().getGameCamera().getyOffset());
        this.dir = Math.atan2(dy, dx);
        this.shoot = mouseManager.isPressed();
    }

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        }
        Rectangle collisionBounds = getCollisionBounds(0, 0);
        Rectangle attackBounds = new Rectangle();
        attackBounds.width = ATTACK_RANGE;
        attackBounds.height = ATTACK_RANGE;

        if (super.getHandler().getKeyManager().up && super.getHandler().getKeyManager().attack) {
            attackBounds.x = collisionBounds.x + collisionBounds.width / 2 - ATTACK_RANGE / 2;
            attackBounds.y = collisionBounds.y - ATTACK_RANGE;
        } else if (super.getHandler().getKeyManager().down && super.getHandler().getKeyManager().attack) {
            attackBounds.x = collisionBounds.x + collisionBounds.width / 2 - ATTACK_RANGE / 2;
            attackBounds.y = collisionBounds.y + collisionBounds.height;
        } else if (super.getHandler().getKeyManager().left && super.getHandler().getKeyManager().attack) {
            attackBounds.x = collisionBounds.x - ATTACK_RANGE;
            attackBounds.y = collisionBounds.y + collisionBounds.height / 2 - ATTACK_RANGE / 2;
        } else if (super.getHandler().getKeyManager().right && super.getHandler().getKeyManager().attack) {
            attackBounds.x = collisionBounds.x + collisionBounds.width;
            attackBounds.y = collisionBounds.y + collisionBounds.height / 2 - ATTACK_RANGE / 2;
        } else {
            return;
        }

        this.attackTimer = 0;

        for (EntityImpl e : super.getHandler().getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0, 0).intersects(attackBounds)) {
                e.hurt(DAMAGE);
                return;
            }
        }
    }

    @Override
    public void die() {
        System.out.println("Dead");
        State.setState(new EndGame(super.getHandler()));
    }

    private void getInput() {
        super.setxMove(0);
        super.setyMove(0);

        if(super.getHandler().getKeyManager().run) {
            playerSpeed = PLAYER_RUN_SPEED;
        } else {
            playerSpeed = PLAYER_DEFAULT_SPEED;
        }

        if (super.getHandler().getKeyManager().up) {
            super.setyMove(super.getyMove() - playerSpeed);
        }
        if (super.getHandler().getKeyManager().down) {
            super.setyMove(super.getyMove() + playerSpeed);
        }
        if (super.getHandler().getKeyManager().left) {
            super.setxMove(super.getxMove() - playerSpeed);
        }
        if (super.getHandler().getKeyManager().right) {
            super.setxMove(super.getxMove() + playerSpeed);
        }
    }

    @Override
    public void render(Graphics g) {
        //x AND y ARE CASTED TO (int) - INTEGER
        //WHY? - TO DRAW IMAGE RAPID TAKES IN INTEGERS AND NOT FLOATS
        g.drawImage(getCurrentAnimationFrame(), (int) (super.getX() - super.getHandler().getGameCamera().getxOffset()),
                (int) (super.getY() - super.getHandler().getGameCamera().getyOffset()), super.getWidth(), super.getHeight(), null);
        inventory.render(g);
        //Code making an red rectangle for collision test purposes.
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                    (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
        drawHealth(g);
        drawMana(g);
    }

    private BufferedImage getCurrentAnimationFrame() {
        if(super.getHandler().getKeyManager().attack) {
            switch(lastMovedDirection) {
                case "Up" :
                    return animUpAttack.getCurrentFrame();
                case "Down":
                    return animDownAttack.getCurrentFrame();
                case "Left":
                    return animLeftAttack.getCurrentFrame();
                case "Right":
                    return animRightAttack.getCurrentFrame();

            }
        }
        if (this.getxMove() < 0) {
            lastMovedDirection = "Left";
            return animLeft.getCurrentFrame();
        } else if (this.getxMove() > 0) {
            lastMovedDirection = "Right";
            return animRight.getCurrentFrame();
        } else if (this.getyMove() < 0) {
            lastMovedDirection = "Up";
            return animUp.getCurrentFrame();
        } else if (this.getyMove() > 0){
            lastMovedDirection = "Down";
            return animDown.getCurrentFrame();
        } else {
            switch (lastMovedDirection) {
                case "Down":
                    return player_DownStill;
                case "Left":
                    return player_LeftStill;
                case "Right":
                    return player_RightStill;
                default:
                    return player_UpStill;
            }
        }

    }

    //health bar
    private void drawHealth(Graphics g){
        g.drawImage(Assets.getFieldElement("playerHealth"), HEART_IMAGE_OFFSET, HEART_IMAGE_OFFSET, HEART_IMAGE_SIZE,HEART_IMAGE_SIZE,null);
        g.setColor(Color.red);
        g.fillRect(HEALTH_BAR_OFFSETS[0],HEALTH_BAR_OFFSETS[1], this.getTotalHealth(), HEALTH_AND_MANA_BAR_THICKNESS);
        g.setColor(Color.green);
        g.fillRect(HEALTH_BAR_OFFSETS[0],HEALTH_BAR_OFFSETS[1] ,this.getHealth(), HEALTH_AND_MANA_BAR_THICKNESS);
    }

    //mana bar
    private void drawMana(Graphics g){
        g.setColor(Color.white);
        g.fillRect(MANA_BAR_OFFSETS[0],MANA_BAR_OFFSETS[1], this.getTotalMana(), HEALTH_AND_MANA_BAR_THICKNESS);
        g.setColor(Color.blue);
        g.fillRect(MANA_BAR_OFFSETS[0],MANA_BAR_OFFSETS[1], this.getMana(), HEALTH_AND_MANA_BAR_THICKNESS);
    }

    public int getTotalHealth(){
        return this.totalHealth;
    }

    public int getTotalMana() {
        return this.totalMana;
    }
    public Inventory getInventory() {
        return inventory;
    }

    public boolean isHasArmor() {
        return hasArmor;
    }

    public int getCurrentHealth(){
        return super.getHealth();
    }
}
