package game;

import game.states.*;
import gfx.Assets;
import gfx.GameCamera;
import music.Sound;


import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

//GAME LOOP CLASS
public class Game implements Runnable {

    private static final int[] INITIAL_XY_OFFSETS = {0, 0};
    private static final int FRAMES_PER_SECOND = 60;
    public static final int SECOND_AS_NANOSECONDS = 1000000000;
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
        this.display = new Display(this.title, this.width, this.height);

        //ADDING KeyListener TO THE frame VARIABLE OF CLASS Display!!!
        //HOW? - BY PASSING THE INSTANCE keyManager OF CLASS InputManager!!!
        this.display.getFrame().addKeyListener(this.keyManager);
        this.display.getFrame().addMouseListener(this.mouseManager);
        this.display.getFrame().addMouseMotionListener(this.mouseManager);
        this.display.getCanvas().addMouseListener(this.mouseManager);
        this.display.getCanvas().addMouseMotionListener(this.mouseManager);

        Assets.init();

        this.handler = new Handler(this);
        this.gameCamera = new GameCamera(this.handler, INITIAL_XY_OFFSETS[0], INITIAL_XY_OFFSETS[1]);

        this.pauseState = new PauseMenu(this.handler);
        this.gameState = new GameState(this.handler);

        this.menuState = new MenuState(this.handler);
        this.aboutState = new AboutState(this.handler);

        State.setState(this.menuState);
    }

    private void tick() {
        this.keyManager.tick();
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

        this.bs = this.display.getCanvas().getBufferStrategy();

        if (this.bs == null) {
            this.display.getCanvas().createBufferStrategy(3);
            return;
        }
        this.g = this.bs.getDrawGraphics();

        //CLEAR THE SCREEN
        this.g.clearRect(INITIAL_XY_OFFSETS[0], INITIAL_XY_OFFSETS[1], this.width, this.height);
        //START DRAWING
        if(State.getState() != null)
            State.getState().render(this.g);

        //END DRAWING
        this.bs.show();
        this.g.dispose();
    }

    public void run() {

        init();
        // NUMBER OF TICKS PER SECOND
        int fps = FRAMES_PER_SECOND;

        //TIME IN NANOSECONDS - 1 SECOND == 1000000000(1 Billion) nanoseconds!
        //timePerTick - THE MAXIMUM AMOUNT OF TIME AVAILABLE FOR EXECUTING ticket() and render() METHODS IN ORDER TO ACHIEVE 60 FRAMES PER SECOND .
        double timePerTick = SECOND_AS_NANOSECONDS /fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        long ticks = 0;
        while (this.running) {
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
            if(timer >= SECOND_AS_NANOSECONDS) {
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
        return this.keyManager;
    }

    public MouseManager getMouseManager() {
        return this.mouseManager;
    }

    public GameCamera getGameCamera() {
        return this.gameCamera;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public synchronized void start() {
        if (this.running) {
            return;
        }
        this.running = true;
        this.thread = new Thread(this);
        this.thread.start();
    }

    public synchronized void stop() throws IOException {
        //Save.exportSave(this.handler.getWorld().getEntityManager().getPlayer().getInventory().getInventoryItems());
        if (!this.running) {
            return;
        }

        this.running = false;

        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public State getGameState() {
        return this.gameState;
    }

    public State getMenuState() {
        return this.menuState;
    }

    public State getAboutState() {
        return this.aboutState;
    }

    public State getPauseState() {
        return this.pauseState;
    }

    public boolean isMuted() {
        return this.isMuted;
    }

    public void setMuted(boolean muted) {
        this.isMuted = muted;
    }


}
