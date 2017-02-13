package entities.creature;

import entities.Entity;
import game.Game;
import game.Handler;
import game.states.EndGame;
import game.states.MenuState;
import game.states.State;
import gfx.Animation;
import gfx.Assets;
import inventory.Inventory;

import java.awt.*;
import java.awt.image.BufferedImage;

//PLAYER CLASS, PLAYER LOGIC GOES HERE
public class Player extends Creature {

    public static int playerSpeed = 2;

    // Animations
    private Animation animLeft, animRight, animUp, animDown;
    // Attack timer
    private long lastAttackTimer, attackCooldown = 600, attackTimer = attackCooldown;
    private Inventory inventory;
    private String lastMovedDirection = "Down";

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        //Player MUST TAKE THE game OBJECT.
        //WHY? - TO GET ACCESS TO THE InputManager's INPUT METHODS(up, down, left, right)!
        //HOW? = BY CALLING Game CLASS METHOD - get.KeyManager().up/down/left/right

        bounds.x = 32;
        bounds.y = 24;
        bounds.width = 24;
        bounds.height = 32;

        //Animations
        animLeft = new Animation(500, Assets.player_Left);
        animRight = new Animation(500, Assets.player_Right);
        animUp = new Animation(500, Assets.player_Up);
        animDown = new Animation(500, Assets.player_Down);

        inventory = new Inventory(handler);
    }

    @Override
    public void tick() {
        //Animations - Movement
        animLeft.tick();
        animRight.tick();
        animUp.tick();
        animDown.tick();

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

        if (handler.getKeyManager().aUp) {
            at.x = cb.x + cb.width / 2 - arSize / 2;
            at.y = cb.y - arSize;
        } else if (handler.getKeyManager().aDown) {
            at.x = cb.x + cb.width / 2 - arSize / 2;
            at.y = cb.y + cb.height;
        } else if (handler.getKeyManager().aLeft) {
            at.x = cb.x - arSize;
            at.y = cb.y + cb.height / 2 - arSize / 2;
        } else if (handler.getKeyManager().aRight) {
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
    }

    private BufferedImage getCurrentAnimationFrame() {
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
