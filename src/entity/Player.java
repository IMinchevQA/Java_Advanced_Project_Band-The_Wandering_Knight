package entity;

import game.Game;
import gfx.Assets;

import java.awt.*;

//PLAYER CLASS, PLAYER LOGIC GOES HERE
public class Player extends Creature {

    public static int playerSpeed = 2;

    public Player(Game game, float x, float y) {
        super(game, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
    }

    @Override
    public void tick() {
        if (game.getKeyManager().up)
            y -= playerSpeed;
        if (game.getKeyManager().down)
            y += playerSpeed;
        if (game.getKeyManager().left)
            x -= playerSpeed;
        if (game.getKeyManager().right)
            x += playerSpeed;
        game.getGameCamera().centerOnEntity(this);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) (x - game.getGameCamera().getxOffset()), (int) (y - game.getGameCamera().getyOffset()), width, height, null);
    }
}
