package items;

import game.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    public static Item[] items = new Item[256];

    private static final int ITEMWIDTH = 38, ITEMHEIGHT = 38;

    protected Handler handler;
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
        bounds = new Rectangle(this.x, this.y, ITEMWIDTH, ITEMHEIGHT);

        items[id] = this;
        this.setPosition(x, y);
    }

    public void tick(){
        if (handler.getWorld().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)){
            if (handler.getKeyManager().pickUp) {
                pickedUp = true;
                if(this.getId() == 3){
                    if(handler.getWorld().getEntityManager().getPlayer().getHealth() > handler.getWorld().getEntityManager().getPlayer().getTotalHealth() - 10){
                        handler.getWorld().getEntityManager().getPlayer().setHealth(handler.getWorld().getEntityManager().getPlayer().getTotalHealth());
                    } else {
                        handler.getWorld().getEntityManager().getPlayer().setHealth(handler.getWorld().getEntityManager().getPlayer().getHealth() + 10);
                    }
                } else if (this.getId() == 0) {
                    handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
                    //int currentCoins = handler.getWorld().getEntityManager().getPlayer().getInventory().getCoins();
                    //if(currentCoins >= 1) {
                    //   System.out.println("Armor activated by: Class Item, row 54!!!");
                    //   handler.getWorld().getEntityManager().getPlayer().changeAnimations_Images();
                    //}
                } else if(this.getId() == 4){
                    /*handler.getWorld().getEntityManager()
                            .getEntities().stream()
                            .filter(x-> (x.getX() == 185 && x.getY() == 550)
                                    || (x.getX() == 1145 && x.getY() == 1090))
                            .forEach(y -> y.removeBounds());*/
                    handler.getWorld().getEntityManager().getPlayer().getInventory().addItem(this);
                }else{
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


    private void setPosition(int x, int y){
        this.x = x;
        this.y = y;
        bounds.x = x;
        bounds.y = y;
    }

    public Handler getHandler() {
        return handler;
    }

    private void setHandler(Handler handler) {
        this.handler = handler;
    }

    private BufferedImage getTexture() {
        return texture;
    }

    /*public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }*/

    public String getName() {
        return name;
    }

    /*public void setName(String name) {
        this.name = name;
    }*/

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    /*public void setX(int x) {
        this.x = x;
    }*/

    public int getY() {
        return y;
    }

    /*public void setY(int y) {
        this.y = y;
    }*/

    public boolean isPickedUp() {
        return pickedUp;
    }
}
