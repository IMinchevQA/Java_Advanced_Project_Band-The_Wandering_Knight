package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KEY EVENTS CLASS

public class InputManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;
    public boolean up, down, right, left;
    public boolean attack, run, pickUp;
    public boolean pause;

    InputManager() {
        this.keys = new boolean[256];
        this.justPressed = new boolean[this.keys.length];
        this.cantPress = new boolean[this.keys.length];
    }

    public void tick() {
        for (int i = 0; i < this.keys.length; i++) {
            if (this.cantPress[i] && !this.keys[i]){
                this.cantPress[i] = false;
            } else if (this.justPressed[i]) {
                this.cantPress[i] = true;
                this.justPressed[i] = false;
            }
            if (!this.cantPress[i] && this.keys[i]){
                this.justPressed[i] = true;
            }
        }

        this.up = this.keys[KeyEvent.VK_W];
        this.down = this.keys[KeyEvent.VK_S];
        this.left = this.keys[KeyEvent.VK_A];
        this.right = this.keys[KeyEvent.VK_D];

        this.pause = this.keys[KeyEvent.VK_ESCAPE];

        //Adding a single one attack button.
        this.attack = this.keys[KeyEvent.VK_SPACE];

        this.pickUp = this.keys[KeyEvent.VK_Q];
        this.run = this.keys[KeyEvent.VK_SHIFT];

    }

    public boolean keyJustPressed(int keyCode){
        if(keyCode < 0 || keyCode >= this.keys.length) {
            return false;
        }
        return this.justPressed[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= this.keys.length){
            return;
        }
        this.keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= this.keys.length)
            return;
        this.keys[e.getKeyCode()] = false;
    }
}
