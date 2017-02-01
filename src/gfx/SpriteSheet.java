package gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

//FOR CROPING SPRITES
public class SpriteSheet {

    private BufferedImage image = null;

    public SpriteSheet(BufferedImage image) {
        this.image = image;
    }
    //STARTING FROM X,Y COORDINATES CROP WIDTH AND HEIGHT RECTANGLE
    public BufferedImage crop(int x, int y, int width, int height) {

        return  image.getSubimage(x, y, width, height);
    }
}
