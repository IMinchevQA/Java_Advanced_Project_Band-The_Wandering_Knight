package game.states;

import game.Handler;
import gfx.Assets;
import gfx.ClickListener;
import gfx.UI.PauseManager;
import gfx.UI.UIImageButton;
import gfx.UI.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;
    private PauseManager pauseManager;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        handler.getMouseManager().setPauseManager(null);
        uiManager.addObject(new UIImageButton(0, 0, 999, 556, Assets.getMenuElement("startScreen"), new ClickListener() {
            @Override
            public void onClick() {

            }
        }));

        uiManager.addObject(new UIImageButton(300, 45, 412, 107, Assets.getMenuElement("btn_start"), new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                handler.getMouseManager().setAboutManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(380, 160, 231, 91, Assets.getMenuElement("btn_about"), new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().aboutState);
            }
        }));

        uiManager.addObject(new UIImageButton(420, 275, 159, 107, Assets.getMenuElement("btn_quit"), new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }
}
