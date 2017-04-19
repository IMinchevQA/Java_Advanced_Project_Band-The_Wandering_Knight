package game.states;

import game.Handler;
import gfx.Assets;
import gfx.ClickListener;
import gfx.UI.PauseManager;
import gfx.UI.UIImageButton;

import java.awt.*;

public class PauseMenu extends State {

    private PauseManager pauseManager;

    public PauseMenu(Handler handler) {
        super(handler);
        pauseManager = new PauseManager(handler);

        handler.getMouseManager().setPauseManager(pauseManager);

        pauseManager.addObject(new UIImageButton(0, 0, 999, 556, Assets.getMenuElement("startScreen"), new ClickListener() {
            @Override
            public void onClick() {

            }
        }));
        pauseManager.addObject(new UIImageButton(900, 150, 52, 52, Assets.getMenuElement("sound"), new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().setMuted(!handler.getGame().isMuted());
                Assets.getMenuElement("sound")[0] = Assets.getMenuElement("soundAlt")[0];
                Assets.getMenuElement("sound")[1]= Assets.getMenuElement("soundAlt")[1];
                Assets.getMenuElement("soundAlt")[1] = Assets.getMenuElement("sound")[0];
                Assets.getMenuElement("soundAlt")[0]= Assets.getMenuElement("sound")[1];
            }
        }));
        pauseManager.addObject(new UIImageButton(800, 470, 198, 91, Assets.getMenuElement("btn_back"), new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGame().getGameState());
                handler.getMouseManager().setPauseManager(null);

            }
        }));
        pauseManager.addObject(new UIImageButton(800, 50, 159, 107, Assets.getMenuElement("btn_quit"), new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
    }

    @Override
    public void tick() {
        pauseManager.tick();
    }

    @Override
    public void render(Graphics g) {
        pauseManager.render(g);
    }
}
