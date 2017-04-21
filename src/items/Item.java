package items;

import game.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    private static final int ID_MEAT_ITEM = 3;
    private static final int MEAT_ITEM_HEALTH_POINTS = 10;
    public static final int ID_COINT_ITEM = 0;
    public static final int ID_KEY_ITEM = 4;
    private static Item[] items = new Item[256];

    private static final int ITEMWIDTH = 38, ITEMHEIGHT = 38;

    private Handler handler;
    private BufferedImage texture;
    private String name;
    private final int id;

    private Rectangle bounds;

    private int x, y;
    private boolean pickedUp = false;

    public Item(BufferedImage texture, String name, int id, int x, int y, Handler handler) {
        this.texture = texture;
        this.name = name;
        this.id = id;
        this.handler = handler;
        this.bounds = new Rectangle(this.x, this.y, ITEMWIDTH, ITEMHEIGHT);

        this.items[id] = this;
        this.setPosition(x, y);
    }

    public void tick(){
        if (this.handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(this.bounds)) {
            if (this.handler.getKeyManager().pickUp) {
                this.pickedUp = true;
                if (this.getId() == ID_MEAT_ITEM) {
                    if (this.handler.getWorld().getEntityManager().getPlayer().getHealth() > handler.getWorld().getEntityManager().getPlayer().getTotalHealth() - MEAT_ITEM_HEALTH_POINTS) {
                        handler.getWorld().getEntityManager().getPlayer().setHealth(handler.getWorld().getEntityManager().getPlayer().getTotalHealth());
                    } else {
                        handler.getWorld().getEntityManager().getPlayer().setHealth(handler.getWorld().getEntityManager().getPlayer().getHealth() + MEAT_ITEM_HEALTH_POINTS);
                    }
                } else if (this.getId() == ID_COINT_ITEM) {
                    this.handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
                    //int currentCoins = handler.getWorld().getEntityManager().getPlayer().getInventory().getCoins();
                    //if(currentCoins >= 1) {
                    //   System.out.println("Armor activated by: Class Item, row 54!!!");
                    //   handler.getWorld().getEntityManager().getPlayer().changeAnimations_Images();
                    //}
                } else if (this.getId() == ID_KEY_ITEM) {
                    /*handler.getWorld().getEntityManager()
                            .getEntities().stream()
                            .filter(x-> (x.getX() == 185 && x.getY() == 550)
                                    || (x.getX() == 1145 && x.getY() == 1090))
                            .forEach(y -> y.removeBounds());*/
                    this.handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
                } else {
                    this.handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
                }
            }
        }
    }

    public void render(Graphics g){
        if (handler == null){
            return;
        }
        render(g, (int) (this.x - this.handler.getGameCamera().getxOffset()), (int) (this.y - this.handler.getGameCamera().getyOffset()));
    }

    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x, y + 20,ITEMWIDTH, ITEMHEIGHT,null);
    }


    private void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        this.bounds.setBounds(x, y, (int)this.bounds.getWidth(), (int)this.bounds.getHeight());
    }

    public Handler getHandler() {
        return this.handler;
    }

    public int getId() {
        return this.id;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isPickedUp() {
        return this.pickedUp;
    }
}
