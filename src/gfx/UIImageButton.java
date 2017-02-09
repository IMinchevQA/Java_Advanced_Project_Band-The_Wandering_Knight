package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Home on 2/9/2017.
 */
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
            g.drawImage(images[1], (int) x, (int) y, width, height, null);
        }else{
            g.drawImage(images[0], (int) x, (int) y, width, height, null);
        }
    }

    @Override
    public void onClick() {
        click.onClick();
    }
}
