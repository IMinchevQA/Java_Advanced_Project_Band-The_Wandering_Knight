package entity;

import game.Game;
import gfx.Assets;

import java.awt.*;

//PLAYER CLASS, PLAYER LOGIC GOES HERE
public class Player extends Creature {
    private Game game;

    public static int playerSpeed = 2;

    public Player(Game game, float x, float y) {
        super(x, y);
        this.game = game;
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
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.player, (int) x, (int) y, null);
    }
}
