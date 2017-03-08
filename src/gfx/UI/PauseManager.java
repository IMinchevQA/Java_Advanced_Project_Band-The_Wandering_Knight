package gfx.UI;

import game.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by Home on 3/8/2017.
 */
public class PauseManager {
    private Handler handler;
    private ArrayList<UIObject> pauseObjects;

    public PauseManager(Handler handler) {
        this.handler = handler;
        this.pauseObjects = new ArrayList<UIObject>();
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UIObject> getObjects() {
        return pauseObjects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.pauseObjects = objects;
    }


    public void tick() {
        for (UIObject o : pauseObjects) {
            o.tick();
        }
    }

    public void render(Graphics g) {
        for (UIObject o : pauseObjects) {
            o.render(g);
        }
    }

    public void onMouseMove(MouseEvent e) {
        for (UIObject o : pauseObjects) {
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for (UIObject o : pauseObjects) {
            o.onMouseRelease(e);
        }
    }

    public void addObject(UIObject o) {
        pauseObjects.add(o);
    }

    public void removeObject(UIObject o) {
        pauseObjects.remove(o);
    }
}
