package game.states;

import game.Handler;
import gfx.Assets;
import gfx.ClickListener;
import gfx.UI.PauseManager;
import gfx.UI.UIImageButton;
import gfx.UI.UIImageWallpaper;
import saves.Save;

import java.awt.*;
import java.io.IOException;

public class PauseMenu extends State {

    private PauseManager pauseManager;

    public PauseMenu(Handler handler) {
        super(handler);
        this.pauseManager = new PauseManager(handler);

        handler.getMouseManager().setPauseManager(pauseManager);

        this.pauseManager.addObject(new UIImageWallpaper(0, 0, 999, 556, Assets.getMenuElement("startScreen")));
        this.pauseManager.addObject(new UIImageButton(900, 150, 52, 52, Assets.getMenuElement("sound"), new ClickListener() {
            @Override
            public void onClick() {
                handler.getGame().setMuted(!handler.getGame().isMuted());
                Assets.getMenuElement("sound")[0] = Assets.getMenuElement("soundAlt")[0];
                Assets.getMenuElement("sound")[1]= Assets.getMenuElement("soundAlt")[1];
                Assets.getMenuElement("soundAlt")[1] = Assets.getMenuElement("sound")[0];
                Assets.getMenuElement("soundAlt")[0]= Assets.getMenuElement("sound")[1];
            }
        }));

        this.pauseManager.addObject(new UIImageButton(800, 470, 198, 91, Assets.getMenuElement("btn_back"), new ClickListener() {
            @Override
            public void onClick() {
                State.setState(handler.getGame().getGameState());
                handler.getMouseManager().setPauseManager(null);

            }
        }));
        this.pauseManager.addObject(new UIImageButton(800, 50, 159, 107, Assets.getMenuElement("btn_quit"), new ClickListener() {
            @Override
            public void onClick() {
                try {
                    Save.exportSave(PauseMenu.super.getHandler().getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems(),
                            PauseMenu.super.getHandler().getWorld().getEntityManager().getPlayer().isHasArmor(),
                            PauseMenu.super.getHandler().getWorld().getEntityManager().getPlayer().getTotalHealth(),
                            PauseMenu.super.getHandler().getWorld().getEntityManager().getPlayer().getCurrentHealth());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        }));
    }

    @Override
    public void tick() {
        this.pauseManager.tick();
    }

    @Override
    public void render(Graphics g) {
        this.pauseManager.render(g);
    }
}
