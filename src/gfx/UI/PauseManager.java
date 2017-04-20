package gfx.UI;

import game.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class PauseManager{
    private Handler handler;
    private ArrayList<UIObject> pauseObjects;

    public PauseManager(Handler handler) {
        this.handler = handler;
        this.pauseObjects = new ArrayList<>();
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }


    public void tick() {
        for (UIObject o : this.pauseObjects) {
            o.tick();
        }
    }

    public void render(Graphics g) {
        for (UIObject o : this.pauseObjects) {
            o.render(g);
        }
    }

    public void onMouseMove(MouseEvent e) {
        for (UIObject o : this.pauseObjects) {
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for (UIObject o : this.pauseObjects) {
            o.onMouseRelease(e);
        }
    }

    public void addObject(UIObject o) {
        this.pauseObjects.add(o);
    }

    public void removeObject(UIObject o) {
        this.pauseObjects.remove(o);
    }
}
