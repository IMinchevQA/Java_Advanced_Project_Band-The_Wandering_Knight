package entity;

import game.Handler;
import gfx.Animation;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

//PLAYER CLASS, PLAYER LOGIC GOES HERE
public class Player extends Creature {

    public static int playerSpeed = 2;

    // Animations
    private Animation animLeft, animRight, animUp, animDown;

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

        //Code making an red rectangle for collision test purposes.
//        g.setColor(Color.red);
//        g.fillRect((int) (x + bounds.x - handler.getGameCamera().getxOffset()),
//                    (int) (y + bounds.y - handler.getGameCamera().getyOffset()),
//                bounds.width, bounds.height);
    }

    private BufferedImage getCurrentAnimationFrame() {
        if (xMove < 0) {
            return animLeft.getCurrentFrame();
        } else if (xMove > 0) {
            return animRight.getCurrentFrame();
        } else if (yMove < 0) {
            return animUp.getCurrentFrame();
        } else {
            return animDown.getCurrentFrame();
        }
    }

}
