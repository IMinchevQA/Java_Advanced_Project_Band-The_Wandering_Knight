package game.states;

import game.Game;
import game.Handler;
import game.Launcher;
import game.MouseManager;
import gfx.*;
import gfx.UI.AboutManager;
import gfx.UI.UIImageButton;
import gfx.UI.UIManager;

import java.awt.*;

/**
 * Created by Ivan Minchev on 2/14/2017.
 */
public class AboutState extends State {

    private AboutManager aboutManager;
    private UIManager uiManager;
    StackTraceElement[] stackTraceElements;

    public AboutState(Handler handler) {
        super(handler);
        this.aboutManager = new AboutManager(handler);
        this.uiManager = handler.getMouseManager().getUiManager();
        handler.getMouseManager().setAboutManager(aboutManager);

        aboutManager.addObject(new UIImageButton(0, 0, 999, 556, Assets.getMenuElement("startScreen"), new ClickListener() {
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
                State.setState(handler.getGame().menuState);
                handler.getMouseManager().setUIManager(uiManager);
            }
        }));
    }


    @Override
    public void tick() {
        aboutManager.tick();
    }

    @Override
    public void render(Graphics g) {
        aboutManager.render(g);
    }



}
