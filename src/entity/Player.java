package entity;

import game.Game;
import gfx.Assets;

import java.awt.*;

//PLAYER CLASS, PLAYER LOGIC GOES HERE
public class Player extends Creature {

    public static int playerSpeed = 2;

    public Player(Game game, float x, float y) {
        super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
        //Player MUST TAKE THE game OBJECT.
        //WHY? - TO GET ACCESS TO THE InputManager's INPUT METHODS(up, down, left, right)!
        //HOW? = BY CALLING Game CLASS METHOD - get.KeyManager().up/down/left/right
    }

    @Override
    public void tick() {
        getInput();
        move();
        game.getGameCamera().centerOnEntity(this);

    }

    public void getInput() {
        xMove = 0;
        yMove = 0;
        if (game.getKeyManager().up)
            y -= playerSpeed;
        if (game.getKeyManager().down)
            y += playerSpeed;
        if (game.getKeyManager().left)
            x -= playerSpeed;
        if (game.getKeyManager().right)
            x += playerSpeed;
    }

    @Override
    public void render(Graphics g) {
        //x AND y ARE CASTED TO (int) - INTEGER
        //WHY? - TO DRAW IMAGE RAPID TAKES IN INTEGERS AND NOT FLOATS
        g.drawImage(Assets.player, (int) (x - game.getGameCamera().getxOffset()),
                                    (int) (y - game.getGameCamera().getyOffset()), width, height, null);
    }
}
