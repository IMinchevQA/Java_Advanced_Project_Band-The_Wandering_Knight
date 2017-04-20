package gfx.UI;

import game.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

public class UIManager{

    private Handler handler;
    private ArrayList<UIObject> objects;

    public UIManager(Handler handler){
        this.handler = handler;
        this.objects = new ArrayList<>();
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void tick(){
        for (UIObject o : this.objects) {
            o.tick();
        }
    }

    public void render(Graphics g){
        for (UIObject o : this.objects) {
            o.render(g);
        }
    }

    public void onMouseMove(MouseEvent e) {
        for (UIObject o : this.objects){
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e){
        for (UIObject o : this.objects) {
            o.onMouseRelease(e);
        }
    }

    public void addObject(UIObject o){
        this.objects.add(o);
    }
}
