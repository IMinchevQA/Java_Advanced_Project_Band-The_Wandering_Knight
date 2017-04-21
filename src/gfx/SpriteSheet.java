package gfx;

import java.awt.image.BufferedImage;

//FOR CROPPING SPRITES
public class SpriteSheet {

    private BufferedImage image = null;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }
    //STARTING FROM X,Y COORDINATES CROP WIDTH AND HEIGHT RECTANGLE
    public BufferedImage crop(int x, int y, int width, int height) {

        return  this.image.getSubimage(x, y, width, height);
    }
}
