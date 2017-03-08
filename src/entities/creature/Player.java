package entities.creature;

import entities.Entity;
import entities.creature.projectile.Projectile;
import game.Handler;
import game.MouseManager;
import game.states.EndGame;
import game.states.State;
import gfx.Animation;
import gfx.Assets;
import inventory.Inventory;
import world.World;

import java.awt.*;
import java.awt.image.BufferedImage;

//PLAYER CLASS, PLAYER LOGIC GOES HERE
public class Player extends Creature {

    public static float playerSpeed = 1;

    // Animations
    private Animation animLeft, animRight, animUp, animDown, animLeftAttack, animRightAttack, animUpAttack, animDownAttack;

    //Still positions
    private static BufferedImage player_LeftStill, player_RightStill, player_UpStill, player_DownStill;

    // Attack timer
    private long lastAttackTimer, attackCooldown = 700, attackTimer = attackCooldown;
    private Inventory inventory;
    private String lastMovedDirection = "Down";
    private int totalHealth;
    private int mana;
    private int totalMana;
    private int time;
    private boolean shoot = false;
    private double dir;
    private boolean hasArmor = false;

    private MouseManager mouseManager = handler.getMouseManager();

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        //Player MUST TAKE THE game OBJECT.
        //WHY? - TO GET ACCESS TO THE InputManager's INPUT METHODS(up, down, left, right)!
        //HOW? = BY CALLING Game CLASS METHOD - get.KeyManager().up/down/left/right
        health = 100;
        totalHealth = health;
        totalMana = 100;
        mana = 100;

        bounds.x = 32;
        bounds.y = 24;
        bounds.width = 24;
        bounds.height = 32;

        //Still positions
        player_LeftStill = Assets.player_LeftStill;
        player_RightStill = Assets.player_RightStill;
        player_UpStill = Assets.player_UpStill;
        player_DownStill = Assets.player_DownStill;

        //Animations
        animLeft = new Animation(400, Assets.player_Left);
        animRight = new Animation(400, Assets.player_Right);
        animUp = new Animation(400, Assets.player_Up);
        animDown = new Animation(400, Assets.player_Down);
        animLeftAttack = new Animation(100, Assets.player_LeftAttack);
        animRightAttack = new Animation(100,Assets.player_RightAttack);
        animDownAttack = new Animation(100, Assets.player_DownAttack);
        animUpAttack = new Animation(100, Assets.player_UpAttack);
        inventory = new Inventory(handler);
    }

    public void classic_Images(){
        //Still positions
        player_LeftStill = Assets.player_LeftStill;
        player_RightStill = Assets.player_RightStill;
        player_UpStill = Assets.player_UpStill;
        player_DownStill = Assets.player_DownStill;

        //Animations
        animLeft = new Animation(400, Assets.player_Left);
        animRight = new Animation(400, Assets.player_Right);
        animUp = new Animation(400, Assets.player_Up);
        animDown = new Animation(400, Assets.player_Down);
        animLeftAttack = new Animation(100, Assets.player_LeftAttack);
        animRightAttack = new Animation(100,Assets.player_RightAttack);
        animDownAttack = new Animation(100, Assets.player_DownAttack);
        animUpAttack = new Animation(100, Assets.player_UpAttack);
        inventory = new Inventory(handler);

        totalHealth = 100;
        hasArmor = false;
    }


    public void changeAnimations_Images() {
//        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
//        System.out.println("CALLED ME");
        animLeft = new Animation(400, Assets.playerArmored_Left);
        animRight = new Animation(400, Assets.playerArmored_Right);
        animUp = new Animation(400, Assets.playerArmored_Up);
        animDown = new Animation(400, Assets.playerArmored_Down);
        animLeftAttack = new Animation(100, Assets.playerArmored_LeftAttack);
        animRightAttack = new Animation(100, Assets.playerArmored_RightAttack);
        animUpAttack = new Animation(100, Assets.playerArmored_UpAttack);
        animDownAttack = new Animation(100, Assets.playerArmored_DownAttack);
        player_LeftStill = Assets.playerArmored_LeftStill;
        player_RightStill = Assets.playerArmored_RightStill;
        player_UpStill = Assets.playerArmored_UpStill;
        player_DownStill = Assets.playerArmored_DownStill;

        totalHealth += 100;
        health += 100;
        hasArmor = true;
        inventory.useCoin();
    }

    @Override
    public void tick() {
        //Animations - Movement
        animLeft.tick();
        animRight.tick();
        animUp.tick();
        animDown.tick();
        animDownAttack.tick();
        animUpAttack.tick();
        animLeftAttack.tick();
        animRightAttack.tick();

        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

        checkAttacks();
        inventory.tick();

        updateShoot();
        time++;
        if(time % 60 == 0){
            if(mana < 100){
                this.mana += 2;
            }
            time = 0;
        }
        if(this.inventory.getCoins() >= 1 && !hasArmor){
            changeAnimations_Images();
        }
        if(health < 100 && hasArmor){
            classic_Images();
        }

    }



    public boolean isShooting() {
        return shoot;
    }

    private void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

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
        double dx = this.mouseManager.getMouseX() - (((int)this.x + 20)- handler.getGameCamera().getxOffset());
        double dy = this.mouseManager.getMouseY() - (((int)this.y + 20)- handler.getGameCamera().getyOffset());
        double direction = Math.atan2(dy, dx);
        this.dir = direction;
        if(mouseManager.isPressed()){
            this.shoot = true;
        }else {
            this.shoot = false;
        }

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

        if (handler.getKeyManager().up && handler.getKeyManager().attack) {
            at.x = cb.x + cb.width / 2 - arSize / 2;
            at.y = cb.y - arSize;
        } else if (handler.getKeyManager().down && handler.getKeyManager().attack) {
            at.x = cb.x + cb.width / 2 - arSize / 2;
            at.y = cb.y + cb.height;
        } else if (handler.getKeyManager().left && handler.getKeyManager().attack) {
            at.x = cb.x - arSize;
            at.y = cb.y + cb.height / 2 - arSize / 2;
        } else if (handler.getKeyManager().right && handler.getKeyManager().attack) {
            at.x = cb.x + cb.width;
            at.y = cb.y + cb.height / 2 - arSize / 2;
        } else {
            return;
        }

        attackTimer = 0;

        for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
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
        State.setState(new EndGame(handler));
    }

    public void getInput() {
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().run) {
            playerSpeed = 2.0f;
        } else {
            playerSpeed = 1.0f;
        }

        if (handler.getKeyManager().up) {
            yMove -= playerSpeed;
        }
        if (handler.getKeyManager().down) {
            yMove += playerSpeed;
        }
        if (handler.getKeyManager().left) {
            xMove -= playerSpeed;
        }
        if (handler.getKeyManager().right) {
            xMove += playerSpeed;
        }
    }

    @Override
    public void render(Graphics g) {
        //x AND y ARE CASTED TO (int) - INTEGER
        //WHY? - TO DRAW IMAGE RAPID TAKES IN INTEGERS AND NOT FLOATS
        g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
                (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
        inventory.render(g);
        //Code making an red rectangle for collision test purposes.
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                    (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
        drawHealth(g);
        drawMana(g);
    }

    public BufferedImage getCurrentAnimationFrame() {
        if(handler.getKeyManager().attack) {
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
        if (xMove < 0) {
            lastMovedDirection = "Left";
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            lastMovedDirection = "Right";
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            lastMovedDirection = "Up";
            return animUp.getCurrentFrame();
        } else if (yMove > 0){
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
        g.drawImage(Assets.playerHealth, 0, 0, 24,24,null);
        g.setColor(Color.red);
        g.fillRect(30,10,totalHealth, 10);
        g.setColor(Color.green);
        g.fillRect(30,10,this.getHealth(), 10);
    }

    private void drawMana(Graphics g){

        g.setColor(Color.white);
        g.fillRect(30,25, totalMana, 10);
        g.setColor(Color.blue);
        g.fillRect(30,25,this.getMana(), 10);
    }

    public int getTotalHealth(){
        return this.totalHealth;
    }
    public void updateTotalHealth(){
        this.totalHealth += 100;
    }
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
