package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KEY EVENTS CLASS

public class InputManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, right, left;

    public InputManager () {
        keys = new boolean[256];
    }

    public void tick() {
        up = keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false; }
}
