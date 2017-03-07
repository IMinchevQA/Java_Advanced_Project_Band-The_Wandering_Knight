package items;

import game.Handler;
import gfx.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    public static Item[] items = new Item[256];
    public static  Item coinItem = new Item(Assets.coin, "coin", 0);
    public static Item woodItem = new Item(Assets.cutDownTree, "Wood", 1);
    public static Item rockItem = new Item(Assets.rockItem, "rock", 2);
    public static Item meatItem = new Item(Assets.meat, "meat", 3);
    public static Item key = new Item(Assets.key, "key", 4);//AY

    public static final int ITEMWIDTH = 38, ITEMHEIGHT = 38;

    protected Handler handler;
    protected BufferedImage texture;
    protected String name;
    protected final int id;

    protected Rectangle bounds;

    protected int x, y;
    protected boolean pickedUp = false;

    private Item(BufferedImage texture, String name, int id) {
        this.texture = texture;
        this.name = name;
        this.id = id;

        bounds = new Rectangle(x, y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
    }

    public void tick(){
        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
            if (handler.getKeyManager().pickUp) {
                pickedUp = true;
                if(this.getId() == 3){
                    if(handler.getWorld().getEntityManager().getPlayer().getHealth() > 90){
                        handler.getWorld().getEntityManager().getPlayer().setHealth(100);
                    } else {
                        handler.getWorld().getEntityManager().getPlayer().setHealth(handler.getWorld().getEntityManager().getPlayer().getHealth() + 10);
                    }
                } else if (this.getId() == 0) {
                    handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
                    int currentCoins = handler.getWorld().getEntityManager().getPlayer().getInventory().getCoins();
                    if(currentCoins >= 1) {
                        System.out.println("Armor activated by: Class Item, row 54!!!");
                        handler.getWorld().getEntityManager().getPlayer().changeAnimations_Images();
                    }
                } else {
                    handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
                }
            }
        }
    }

    public void render(Graphics g){
        if (handler == null){
            return;
        }
        render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y + 20,ITEMWIDTH, ITEMHEIGHT,null);
    }

    public Item createNew(int x, int y){
        Item i = new Item(texture, name, id);
        i.setPosition(x, y + 20);
        return i;
    }

    public void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    private BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }
}
