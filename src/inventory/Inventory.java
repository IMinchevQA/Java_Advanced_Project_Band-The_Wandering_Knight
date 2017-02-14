
//TODO : update the count of each item

package inventory;

import game.Handler;
import gfx.Assets;
import items.Item;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;

    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<>();
    }

    public void tick() {
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
            active = !active;
        }
        if (!active) {
            return;
        }

    }

    public void render(Graphics g) {
        if (!active) {
            return;
        }

        drawInventory(g);
    }

    public void addItem(Item item) {

        for (Item i : inventoryItems) {
            if (i.getId() == item.getId()) {
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }


    public void drawInventory(Graphics g){

        int invX = handler.getWorld().getEntityManager().getPlayer().getTotalHealth() + 40;

        //inventory background
        g.drawImage(Assets.inventory, invX, 10, 600, 50, null);

        g.setColor(Color.white);
        Font f = new Font("Dialog", Font.BOLD, 12);
        g.setFont(f);

        //coin item
        g.drawImage(Assets.coin, invX + 5 , 15, 42,42,null);
        g.drawString("1", invX + 50, 50);

        //wood item
        g.drawImage(Assets.cutDownTree, invX + 55,20, 42,42,null);
        g.drawString("1", invX+100, 50);

    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
