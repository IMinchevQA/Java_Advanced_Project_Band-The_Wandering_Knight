package items;

import game.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ItemManager {

    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler) {
        this.handler = handler;
        this.items = new ArrayList<>();
    }

    public void tick() {
        Iterator<Item> it = this.items.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            i.tick();
            if (i.isPickedUp()) {
                it.remove();
            }
        }
    }

    public void render(Graphics g) {
        for (Item item : this.items) {
            item.render(g);
        }
    }

    public void addItem(Item i) {
        this.items.add(i);
    }

    public Handler getHandler() {
        return this.handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;

    }
}
