package gfx;

import java.awt.image.BufferedImage;


public class Animations {
    private int speed, index;
    private BufferedImage[] frames;

    private long lastTime, timer;

    public Animations(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        lastTime = System.currentTimeMillis();
        timer = 0;
    }

    public void update() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            index++;
            timer = 0;
            if(index >= frames.length) {
                index = 0;
            }
        }

    }


    public BufferedImage getCurrentFrame() {
        return frames[index];
    }

    public int getIndex() {
        return index;
    }
}
