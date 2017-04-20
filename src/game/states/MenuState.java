package game.states;

import game.Handler;
import gfx.Assets;
import gfx.ClickListener;
import gfx.UI.UIImageButton;
import gfx.UI.UIImageWallpaper;
import gfx.UI.UIManager;
import saves.Save;
import saves.SaveObject;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Handler handler){
        super(handler);
        this.uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(this.uiManager);
        handler.getMouseManager().setPauseManager(null);


        this.uiManager.addObject(new UIImageWallpaper(0, 0, 999, 556, Assets.getMenuElement("startScreen")));

        this.uiManager.addObject(new UIImageButton(300, 45, 412, 107, Assets.getMenuElement("btn_start"), new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                handler.getMouseManager().setAboutManager(null);
                State.setState(handler.getGame().getGameState());
            }
        }));

        this.uiManager.addObject(new UIImageButton(300, 160, 412, 107, Assets.getMenuElement("btn_load"), new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                handler.getMouseManager().setAboutManager(null);
                handler.getGame().getGameState().getHandler().getWorld().savedPlayer(Save.importSave());
                State.setState(handler.getGame().getGameState());

            }
        }));

        this.uiManager.addObject(new UIImageButton(420, 275, 231, 91, Assets.getMenuElement("btn_about"), new ClickListener() {
            @Override
            public void onClick() {
                handler.getMouseManager().setUIManager(null);
                State.setState(handler.getGame().getAboutState());
            }
        }));

        this.uiManager.addObject(new UIImageButton(460, 375, 159, 107, Assets.getMenuElement("btn_quit"), new ClickListener() {
            @Override
            public void onClick() {
                System.exit(0);
            }
        }));
    }

    @Override
    public void tick() {
        this.uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        this.uiManager.render(g);
    }
}
