package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KEY EVENTS CLASS

public class InputManager implements KeyListener {

    private boolean[] keys, justPressed, cantPress;
    public boolean up, down, right, left;
    public boolean attack, run, pickUp;

    public InputManager () {
        keys = new boolean[256];
        justPressed = new boolean[keys.length];
        cantPress = new boolean[keys.length];
    }

    public void tick() {
        for (int i = 0; i < keys.length; i++) {
            if (cantPress[i] && !keys[i]){
                cantPress[i] = false;
            } else if (justPressed[i]){
                cantPress[i] = true;
                justPressed[i] = false;
            }
            if (!cantPress[i] && keys[i]){
                justPressed[i] = true;
            }
        }

        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];

        //Adding a single one attack button.
        attack = keys[KeyEvent.VK_D];

        pickUp = keys[KeyEvent.VK_Q];
        run = keys[KeyEvent.VK_S];

    }

    public boolean keyJustPressed(int keyCode){
        if(keyCode < 0 || keyCode >= keys.length) {
            return false;
        }
        return justPressed[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length){
            return;
        }
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
            return;
        keys[e.getKeyCode()] = false;
    }
}
