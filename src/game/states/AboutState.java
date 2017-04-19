package game.states;

import game.Handler;
import gfx.Assets;
import gfx.ClickListener;
import gfx.UI.AboutManager;
import gfx.UI.UIImageButton;
import gfx.UI.UIManager;

import java.awt.*;

public class AboutState extends State {

    private AboutManager aboutManager;
    private UIManager uiManager;

    public AboutState(Handler handler) {
        super(handler);
        this.aboutManager = new AboutManager(handler);
        this.uiManager = handler.getMouseManager().getUiManager();
        handler.getMouseManager().setAboutManager(aboutManager);

        this.aboutManager.addObject(new UIImageButton(0, 0, 999, 556, Assets.getMenuElement("startScreen"), new ClickListener() {
            @Override
            public void onClick() {

            }
        }));


        aboutManager.addObject(new UIImageButton(0, 0, 999, 556, Assets.getMenuElement("infoTeam"), new ClickListener() {
            @Override
            public void onClick() {

            }
        }));


        aboutManager.addObject(new UIImageButton(800, 470, 198, 91, Assets.getMenuElement("btn_back"), new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGame().getMenuState());
                handler.getMouseManager().setUIManager(uiManager);
            }
        }));
    }


    @Override
    public void tick() {
        this.aboutManager.tick();
    }

    @Override
    public void render(Graphics g) {
        this.aboutManager.render(g);
    }
}
