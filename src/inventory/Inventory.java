package inventory;

import game.Handler;
import gfx.Assets;
import items.Item;
import items.Itemable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private HashMap<Integer, Integer> inventoryItems; //the key is the item id, the value is the count

    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new HashMap<>();
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
        int id = item.getId();
        if (inventoryItems.containsKey(id)) {
            inventoryItems.put(id, inventoryItems.get(id) + 1);
        } else {
            inventoryItems.put(id, 1);
        }
    }


    public int getCoins() {
        //test
        this.inventoryItems.putIfAbsent(0, 0);
        return this.inventoryItems.get(0);
    }

    public void useCoin(){
        if(inventoryItems.containsKey(0)){
            inventoryItems.put(0, inventoryItems.get(0) - 1);
        }
    }

    private String itemCountToString(int id){
        if(inventoryItems.containsKey(id)){
            return String.valueOf(inventoryItems.get(id));
        } else {
            return "0";
        }
    }

    public int getKey(){
        this.inventoryItems.putIfAbsent(4, 0);
        return this.inventoryItems.get(4);
    }


    private  void drawInventory(Graphics g) {

        int invX = handler.getWorld().getEntityManager().getPlayer().getTotalHealth() + 40;

        //inventory background
        g.drawImage(Assets.getFieldElement("inventory"), invX, 10, 600, 50, null);

        g.setColor(Color.white);
        Font f = new Font("Dialog", Font.BOLD, 12);
        g.setFont(f);

        //coin item
        g.drawImage(Assets.getFieldElement("coin"), invX + 5, 15, 42, 42, null);
        String coinCountString = itemCountToString(Itemable.COIN.getId());
        g.drawString(coinCountString, invX + 50, 50);

        //wood item
        g.drawImage(Assets.getFieldElement("cutDownTree"), invX + 55, 20, 42, 42, null);
        String woodCountString = itemCountToString(Itemable.WOOD.getId());
        g.drawString(woodCountString, invX + 100, 50);

        //rock item
        g.drawImage(Assets.getFieldElement("rockItem"), invX + 110, 20,42,42,null);
        String rockCountString = itemCountToString(Itemable.ROCK.getId());
        g.drawString(rockCountString, invX + 150, 50);

        //key item
        g.drawImage(Assets.getFieldElement("key"), invX  + 165, 20, 25,25,null);//AY
        String keyCountString = itemCountToString(Itemable.KEY.getId());//AY
        g.drawString(keyCountString, invX + 200, 50);//AY

    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
