package game.states;

import game.Handler;
import gfx.Assets;

import java.awt.*;

/**
 * Created by Home on 2/14/2017.
 */
public class EndGame extends State {


    public EndGame(Handler handler){
        super(handler);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.endGame, 200, 200, 178, 178, null );
    }
}
