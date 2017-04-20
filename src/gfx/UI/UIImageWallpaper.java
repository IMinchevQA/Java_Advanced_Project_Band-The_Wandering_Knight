package gfx.UI;

import gfx.ClickListener;

import java.awt.*;
import java.awt.image.BufferedImage;


public class UIImageWallpaper extends UIObject {
    private BufferedImage[] images;
    private ClickListener click;

    public UIImageWallpaper(float x, float y, int width, int height, BufferedImage[] images) {
        super(x, y, width, height);
        this.images = images;
        this.click = new ClickListener() {
            @Override
            public void onClick() {
            }
        };
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(super.isHovering()){
            //Added if block to avoid exception when load BufferedImage with only 1 element = startScreen.
            if (this.images.length == 1) {
                g.drawImage(this.images[0], (int) super.getX(), (int) super.getY(), super.getWidth(), super.getHeight(), null);
            } else {
                g.drawImage(this.images[1], (int) super.getX(), (int) super.getY(), super.getWidth(), super.getHeight(), null);
            }
        }else{
            g.drawImage(this.images[0], (int) super.getX(), (int) super.getY(), super.getWidth(), super.getHeight(), null);
        }
    }

    @Override
    public void onClick() {
        System.out.println();
        this.click.onClick();
    }
}
