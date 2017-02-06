package entity;

import game.Game;
import game.Handler;
import gfx.Assets;

import java.awt.*;

//PLAYER CLASS, PLAYER LOGIC GOES HERE
public class Player extends Creature {

    public static int playerSpeed = 2;

    public Player(Handler handler, float x, float y) {
        super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        //Player MUST TAKE THE game OBJECT.
        //WHY? - TO GET ACCESS TO THE InputManager's INPUT METHODS(up, down, left, right)!
        //HOW? = BY CALLING Game CLASS METHOD - get.KeyManager().up/down/left/right
    }

    @Override
    public void tick() {
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);

    }

    public void getInput() {
        xMove = 0;
        yMove = 0;
        if (handler.getKeyManager().up)
            y -= playerSpeed;
        if (handler.getKeyManager().down)
            y += playerSpeed;
        if (handler.getKeyManager().left)
            x -= playerSpeed;
        if (handler.getKeyManager().right)
            x += playerSpeed;
    }

    @Override
    public void render(Graphics g) {
        //x AND y ARE CASTED TO (int) - INTEGER
        //WHY? - TO DRAW IMAGE RAPID TAKES IN INTEGERS AND NOT FLOATS
        g.drawImage(Assets.player, (int) (x - handler.getGameCamera().getxOffset()),
                                    (int) (y - handler.getGameCamera().getyOffset()), width, height, null);
    }
}
