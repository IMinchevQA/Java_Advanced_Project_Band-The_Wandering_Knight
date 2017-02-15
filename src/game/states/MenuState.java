package game.states;

import game.Handler;
import gfx.Assets;
import gfx.ClickListener;
import gfx.UI.UIImageButton;
import gfx.UI.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(0, 0, 999, 556, Assets.startScreen, new ClickListener() {
            @Override
            public void onClick() {

            }
        }));

        uiManager.addObject(new UIImageButton(300, 20, 256, 256, Assets.btn_start, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                handler.getMouseManager().setAboutManager(null);
                State.setState(handler.getGame().gameState);
            }
        }));

        uiManager.addObject(new UIImageButton(300, 300, 256, 118, Assets.btn_about, new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().aboutState);
            }
        }));

        uiManager.addObject(new UIImageButton(300, 450, 256, 118, Assets.btn_quit, new ClickListener() {
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
