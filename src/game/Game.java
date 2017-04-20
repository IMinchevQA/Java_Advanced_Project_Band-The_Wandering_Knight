package game;

import game.states.*;
import gfx.Assets;
import gfx.GameCamera;
import music.Sound;
import saves.Save;


import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

//GAME LOOP CLASS
public class Game implements Runnable {

    private String title;
    private int width, height;

    private Display display;
    private boolean running = false;
    private Thread thread;
    private Sound sound;
    private boolean isMuted;

    //BufferStrategy - Could be considered as a way for the computer to draw things to the screen.
    //Figuratively it is a kind of a "Hidden" screen within the computer drawn before to be displayed on the monitor.
    //Purpose:To prevent flickering in result of drawing directly on the monitor.
    private BufferStrategy bs;
    private Graphics g;

    //States
    private State gameState;
    private State menuState;
    private State aboutState;
    private State pauseState;

    //Input
    private InputManager keyManager;
    private MouseManager mouseManager;

    //Camera
    private GameCamera gameCamera;

    //Handler
    private Handler handler;

    Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.keyManager = new InputManager();
        this.mouseManager = new MouseManager();
        this.setMuted(false);
        this.sound = new Sound();
    }

    private void init() {
        display = new Display(title, width, height);

        //ADDING KeyListener TO THE frame VARIABLE OF CLASS Display!!!
        //HOW? - BY PASSING THE INSTANCE keyManager OF CLASS InputManager!!!
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Assets.init();

        handler = new Handler(this);
        gameCamera = new GameCamera(handler, 0, 0);

        pauseState = new PauseMenu(this.handler);
        gameState = new GameState(this.handler);

        menuState = new MenuState(this.handler);
        aboutState = new AboutState(this.handler);

        State.setState(menuState);
    }

    private void tick() {
        keyManager.tick();
        if(State.getState() != null) {
            State.getState().tick();
        }
        if(!isMuted()){
            this.sound.startMusic();
        }else{
            this.sound.stopMusic();
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
        // NUMBER OF TICKS PER SECOND
        int fps = 60;

        //TIME IN NANOSECONDS - 1 SECOND == 1000000000(1 Billion) nanoseconds!
        //timePerTick - THE MAXIMUM AMOUNT OF TIME AVAILABLE FOR EXECUTING ticket() and render() METHODS IN ORDER TO ACHIEVE 60 FRAMES PER SECOND .
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        long ticks = 0;
        while(running) {
            now = System.nanoTime();

            //TIME AVAILABLE UNTIL tick() and render() METHODS GETS CALLED AGAIN!!!
            //BOTH METHODS ARE CALLED ONLY WHENEVER delta GETS TO THE VALUE OF 1!!!
            //AFTER A.M METHODS ARE CALLED A NUMBER OF 1 IS SUBTRACTED FROM delta!!!
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

            //OPTIONAL CODE
            if(timer >= 1000000000) {
//               System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
        try {
            stop();//A-10
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public InputManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start() {
        if(running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() throws IOException {
        //Save.exportSave(this.handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems());
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public State getGameState() {
        return gameState;
    }

    public State getMenuState() {
        return menuState;
    }

    public State getAboutState() {
        return aboutState;
    }

    public State getPauseState() {
        return pauseState;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }


}
