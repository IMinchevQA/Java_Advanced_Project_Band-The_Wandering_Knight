package game.states;

import game.Handler;
import gfx.Assets;

import java.awt.*;

public class EndGame extends State {

    public EndGame(Handler handler){
        super(handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.getFieldElement("endGame"), 200, 200, 178, 178, null );
    }
}
