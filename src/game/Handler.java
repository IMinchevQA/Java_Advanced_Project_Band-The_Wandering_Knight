package game;


import gfx.GameCamera;
import world.World;

public class Handler {

    private Game game;
    private World world;

    public Handler(Game game) {
        this.game = game;
    }

    public GameCamera getGameCamera() {
        return this.game.getGameCamera();
    }

    public InputManager getKeyManager() {
        return this.game.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return this.game.getMouseManager();
    }

    public int getWidth() {
        return this.game.getWidth();
    }

    public int getHeight() {
        return this.game.getHeight();
    }

    public Game getGame() {
        return this.game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return this.world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

}
