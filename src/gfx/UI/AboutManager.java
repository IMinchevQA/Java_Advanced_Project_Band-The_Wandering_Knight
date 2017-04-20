package gfx.UI;

import game.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AboutManager{


    private Handler handler;
    private ArrayList<UIObject> aboutObjects;

    public AboutManager(Handler handler) {
        this.handler = handler;
        this.aboutObjects = new ArrayList<>();
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }


    public void tick() {
        for (UIObject o : this.aboutObjects) {
            o.tick();
        }
    }

    public void render(Graphics g) {
        for (UIObject o : this.aboutObjects) {
            o.render(g);
        }
    }

    public void onMouseMove(MouseEvent e) {
        for (UIObject o : this.aboutObjects) {
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for (UIObject o : this.aboutObjects) {
            o.onMouseRelease(e);
        }
    }

    public void addObject(UIObject o) {
        this.aboutObjects.add(o);
    }


}
