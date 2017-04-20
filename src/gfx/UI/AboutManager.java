package gfx.UI;

import game.Handler;
import gfx.UI.UIObject;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ivan Minchev on 2/14/2017.
 */
public class AboutManager{


    private Handler handler;
    private ArrayList<UIObject> aboutObjects;

    public AboutManager(Handler handler) {
        this.handler = handler;
        this.aboutObjects = new ArrayList<UIObject>();
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<UIObject> getObjects() {
        return aboutObjects;
    }

    public void setObjects(ArrayList<UIObject> objects) {
        this.aboutObjects = objects;
    }


    public void tick() {
        for (UIObject o : aboutObjects) {
            o.tick();
        }
    }

    public void render(Graphics g) {
        for (UIObject o : aboutObjects) {
            o.render(g);
        }
    }

    public void onMouseMove(MouseEvent e) {
        for (UIObject o : aboutObjects) {
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for (UIObject o : aboutObjects) {
            o.onMouseRelease(e);
        }
    }

    public void addObject(UIObject o) {
        aboutObjects.add(o);
    }

    public void removeObject(UIObject o) {
        aboutObjects.remove(o);
    }


}
