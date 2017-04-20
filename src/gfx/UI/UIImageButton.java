package gfx.UI;

import gfx.ClickListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class UIImageButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener click;

    public UIImageButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener click) {
        super(x, y, width, height);
        this.images = images;
        this.click = click;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(hovering){
            //Added if block to avoid exception when load BufferedImage with only 1 element = startScreen.
            if (this.images.length == 1) {
                g.drawImage(images[0], (int) x, (int) y, width, height, null);
            } else {
                g.drawImage(images[1], (int) x, (int) y, width, height, null);
            }
        }else{
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void onClick() {
        click.onClick();
    }
}
