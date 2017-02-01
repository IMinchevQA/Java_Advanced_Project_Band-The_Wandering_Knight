package game;

import gfx.Assets;

import javax.net.ssl.KeyManager;
import java.awt.*;
import java.awt.image.BufferStrategy;

//GAME LOOP CLASS
public class Game implements Runnable {

    public String title;
    public int width, height;

    private Display display;
    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    private State gameState;

    private InputManager keyManager;

    public Game(String title, int width, int height) {

        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new InputManager();

    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        gameState = new GameState(this);
        State.setState(gameState);
    }

    private void tick() {
        keyManager.tick();
        if(State.getState() != null) {
            State.getState().tick();
        }

    }

    private void render() {

        bs = display.getCanvas().getBufferStrategy();

        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //CLEAR THE SCREEN
        g.clearRect(0, 0, width, height);
        //START DRAWING
        if(State.getState() != null)
            State.getState().render(g);

        //END DRAWING
        bs.show();
        g.dispose();
    }

    public void run() {

        init();
        // tick per second
        int fps = 60;
        //A SECOND IN MILLISECONDS
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        long ticks = 0;
        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            //TIME PASSED SINCE LAST ITERATION OF THE LOOP
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if(timer >= 1000000000) {
                System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }

    }

    public InputManager getKeyManager() {
        return keyManager;
    }

    public synchronized void start() {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
