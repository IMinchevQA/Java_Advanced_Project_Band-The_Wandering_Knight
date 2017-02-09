package game;

import java.awt.*;

/**
 * Created by Home on 2/9/2017.
 */
public class MenuState extends State{
    public MenuState(Handler handler){
        super(handler);
    }

    @Override
    public void tick() {
        if(handler.getMouseManager().isLeftPressed()){
            State.setState(handler.getGame().gameState);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY(),
                15, 15);
    }
}
