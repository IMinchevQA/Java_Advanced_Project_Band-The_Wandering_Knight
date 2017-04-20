package game.states;

import game.Handler;

import java.awt.*;

//ABSTRACT STATE CLASS
public abstract class State {

    private static State currentState = null;
    private Handler handler;

    public State(Handler handler) {
        this.handler = handler;
    }

    protected Handler getHandler() {
        return this.handler;
    }

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState() {
        return currentState;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
}
