package entities.creature;

import entities.EntityImpl;
import game.Handler;
import game.MouseManager;
import game.states.EndGame;
import game.states.State;
import gfx.Animation;
import gfx.Assets;
import inventory.Inventory;

import java.awt.*;
import java.awt.image.BufferedImage;

//PLAYER CLASS, PLAYER LOGIC GOES HERE
public class Player extends Creature {

    private static final int MAX_VALUE_OF_MANA = 100;
    private static final int SECONDS_DIVISOR = 60;
    private static final int MANA_ADDEND = 2;
    private static final int MIN_VALUE_OF_HEALTH_TO_BE_ARMORED = 100;
    private static final int ARMOR_COINS_NECESSARY = 1;
    private static float playerSpeed = 1;

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
        super.setHealth(100);
        this.totalHealth = super.getHealth();
        this.totalMana = 100;
        this.mana = 100;
        super.getBoundsRect().setBounds(32, 24, 24, 32);


        //Still positions
        player_LeftStill = Assets.getPlayerStillPositions("player_LeftStill");
        player_RightStill = Assets.getPlayerStillPositions("player_RightStill");
        player_UpStill = Assets.getPlayerStillPositions("player_UpStill");
        player_DownStill = Assets.getPlayerStillPositions("player_DownStill");

        //Animations
        this.animLeft = new Animation(400, Assets.getPlayerMotionPositions("player_Left"));
        this.animRight = new Animation(400, Assets.getPlayerMotionPositions("player_Right"));
        this.animUp = new Animation(400, Assets.getPlayerMotionPositions("player_Up"));
        this.animDown = new Animation(400, Assets.getPlayerMotionPositions("player_Down"));
        this.animLeftAttack = new Animation(100, Assets.getPlayerMotionPositions("player_LeftAttack"));
        this.animRightAttack = new Animation(100,Assets.getPlayerMotionPositions("player_RightAttack"));
        this.animDownAttack = new Animation(100, Assets.getPlayerMotionPositions("player_DownAttack"));
        this.animUpAttack = new Animation(100, Assets.getPlayerMotionPositions("player_UpAttack"));
        this.inventory = new Inventory(handler);
    }

    private void classic_Images(){
        //Still positions
        player_LeftStill = Assets.getPlayerStillPositions("player_LeftStill");
        player_RightStill = Assets.getPlayerStillPositions("player_RightStill");
        player_UpStill = Assets.getPlayerStillPositions("player_UpStill");
        player_DownStill = Assets.getPlayerStillPositions("player_DownStill");

        //Animations
        this.animLeft = new Animation(400, Assets.getPlayerMotionPositions("player_Left"));
        this.animRight = new Animation(400, Assets.getPlayerMotionPositions("player_Right"));
        this.animUp = new Animation(400, Assets.getPlayerMotionPositions("player_Up"));
        this.animDown = new Animation(400, Assets.getPlayerMotionPositions("player_Down"));
        this.animLeftAttack = new Animation(100, Assets.getPlayerMotionPositions("player_LeftAttack"));
        this.animRightAttack = new Animation(100,Assets.getPlayerMotionPositions("player_RightAttack"));
        this.animDownAttack = new Animation(100, Assets.getPlayerMotionPositions("player_DownAttack"));
        this.animUpAttack = new Animation(100, Assets.getPlayerMotionPositions("player_UpAttack"));
        this.inventory = new Inventory(super.getHandler());

        this.totalHealth = 100;
        this.hasArmor = false;
    }


    private void changeAnimations_Images() {
        this.animLeft = new Animation(400, Assets.getPlayerMotionPositions("playerArmored_Left"));
        this.animRight = new Animation(400, Assets.getPlayerMotionPositions("playerArmored_Right"));
        this.animUp = new Animation(400, Assets.getPlayerMotionPositions("playerArmored_Up"));
        this.animDown = new Animation(400, Assets.getPlayerMotionPositions("playerArmored_Down"));
        this.animLeftAttack = new Animation(100, Assets.getPlayerMotionPositions("playerArmored_LeftAttack"));
        this.animRightAttack = new Animation(100, Assets.getPlayerMotionPositions("playerArmored_RightAttack"));
        this.animUpAttack = new Animation(100, Assets.getPlayerMotionPositions("playerArmored_UpAttack"));
        this.animDownAttack = new Animation(100, Assets.getPlayerMotionPositions("playerArmored_DownAttack"));
        player_LeftStill = Assets.getPlayerStillPositions("playerArmored_LeftStill");
        player_RightStill = Assets.getPlayerStillPositions("playerArmored_RightStill");
        player_UpStill = Assets.getPlayerStillPositions("playerArmored_UpStill");
        player_DownStill = Assets.getPlayerStillPositions("playerArmored_DownStill");

        this.totalHealth += 100;
        super.setHealth(super.getHealth() + 100);
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
                this.mana += MANA_ADDEND;
            }
            time = 0;
        }
        if(this.inventory.getCoins() >= ARMOR_COINS_NECESSARY && !hasArmor){
            changeAnimations_Images();
        }

        if(super.getHealth() < MIN_VALUE_OF_HEALTH_TO_BE_ARMORED && this.hasArmor){
            classic_Images();
        }

    }

    public boolean isShooting() {
        return shoot;
    }

//    private void setShoot(boolean shoot) {
//        this.shoot = shoot;
//    }

    public double getDir() {
        return dir;
    }

    private void setDir(double dir) {
        this.dir = dir;
    }

    public int getMana() {
        return mana;
    }

    public void takeMana() {
        this.mana = this.getMana() - 5;
    }

    private void updateShoot(){
        double dx = this.mouseManager.getMouseX() - (((int)super.getX() + 20)- super.getHandler().getGameCamera().getxOffset());
        double dy = this.mouseManager.getMouseY() - (((int)super.getY() + 20)- super.getHandler().getGameCamera().getyOffset());
        this.dir = Math.atan2(dy, dx);
        this.shoot = mouseManager.isPressed();
    }

    private void checkAttacks() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if (attackTimer < attackCooldown) {
            return;
        }
        Rectangle cb = getCollisionBounds(0, 0);
        Rectangle at = new Rectangle();
        int arSize = 20;
        at.width = arSize;
        at.height = arSize;

        if (super.getHandler().getKeyManager().up && super.getHandler().getKeyManager().attack) {
            at.x = cb.x + cb.width / 2 - arSize / 2;
            at.y = cb.y - arSize;
        } else if (super.getHandler().getKeyManager().down && super.getHandler().getKeyManager().attack) {
            at.x = cb.x + cb.width / 2 - arSize / 2;
            at.y = cb.y + cb.height;
        } else if (super.getHandler().getKeyManager().left && super.getHandler().getKeyManager().attack) {
            at.x = cb.x - arSize;
            at.y = cb.y + cb.height / 2 - arSize / 2;
        } else if (super.getHandler().getKeyManager().right && super.getHandler().getKeyManager().attack) {
            at.x = cb.x + cb.width;
            at.y = cb.y + cb.height / 2 - arSize / 2;
        } else {
            return;
        }

        attackTimer = 0;

        for (EntityImpl e : super.getHandler().getWorld().getEntityManager().getEntities()) {
            if (e.equals(this)) {
                continue;
            }
            if (e.getCollisionBounds(0, 0).intersects(at)) {
                e.hurt(1);
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
            playerSpeed = 2.0f;
        } else {
            playerSpeed = 1.0f;
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
        g.drawImage(Assets.getFieldElement("playerHealth"), 0, 0, 24,24,null);
        g.setColor(Color.red);
        g.fillRect(30,10,totalHealth, 10);
        g.setColor(Color.green);
        g.fillRect(30,10,this.getHealth(), 10);
    }

    private void drawMana(Graphics g){

        g.setColor(Color.white);
        g.fillRect(30,25, this.totalMana, 10);
        g.setColor(Color.blue);
        g.fillRect(30,25,this.getMana(), 10);
    }

    public int getTotalHealth(){
        return this.totalHealth;
    }
//    public void updateTotalHealth(){
//        this.totalHealth += 100;
//    }
    public Inventory getInventory() {
        return inventory;
    }

//    public void setInventory(Inventory inventory) {
//        this.inventory = inventory;
//    }
}
