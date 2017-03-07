package entities.creature;

import entities.Entity;
import game.Handler;
import game.states.EndGame;
import game.states.State;
import gfx.Animation;
import gfx.Assets;
import inventory.Inventory;

import java.awt.*;
import java.awt.image.BufferedImage;

//PLAYER CLASS, PLAYER LOGIC GOES HERE
public class Player extends Creature {

    public static float playerSpeed = 1;

    // Animations
    private Animation animLeft, animRight, animUp, animDown, animLeftAttack, animRightAttack, animUpAttack, animDownAttack;
    // Attack timer
    private long lastAttackTimer, attackCooldown = 700, attackTimer = attackCooldown;
    private Inventory inventory;
    private String lastMovedDirection = "Down";
    public final int totalHealth;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        //Player MUST TAKE THE game OBJECT.
        //WHY? - TO GET ACCESS TO THE InputManager's INPUT METHODS(up, down, left, right)!
        //HOW? = BY CALLING Game CLASS METHOD - get.KeyManager().up/down/left/right
        health = 100;
        totalHealth = health;

        bounds.x = 32;
        bounds.y = 24;
        bounds.width = 24;
        bounds.height = 32;

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
                    return Assets.player_DownStill;
                case "Left":
                    return Assets.player_LeftStill;
                case "Right":
                    return Assets.player_RightStill;
                default:
                    return Assets.player_UpStill;
            }
        }

    }
    //health bar
    public void drawHealth(Graphics g){
        g.drawImage(Assets.playerHealth, 0, 0, 24,24,null);
        g.setColor(Color.red);
        g.fillRect(30,10,totalHealth, 10);
        g.setColor(Color.green);
        g.fillRect(30,10,this.getHealth(), 10);
    }

    public int getTotalHealth(){
        return this.totalHealth;
    }
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
