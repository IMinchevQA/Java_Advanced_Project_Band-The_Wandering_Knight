package gfx;


import java.awt.image.BufferedImage;

public class Animation {
    private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        this.index = 0;
        this.timer = 0;
        this.lastTime = System.currentTimeMillis();
    }

    public void tick() {
        this.timer += System.currentTimeMillis() - lastTime;
        this.lastTime = System.currentTimeMillis();

        if(this.timer > this.speed) {
            this.index++;
            this.timer = 0;
            if (this.index > this.frames.length - 1) {
                this.index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        return this.frames[this.index];
    }
}
