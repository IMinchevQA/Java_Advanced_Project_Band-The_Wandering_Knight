package game.states;

import entities.creature.Player;
import entities.statics.Tree;
import game.Handler;

import gfx.UI.PauseManager;
import world.World;

import java.awt.*;

//GAME STATE, WILL GET USEFUL IF WE HAVE OTHER STATES LIKE MENU STATE, RENDER LOGIC GOES HERE
public class GameState extends State {

    private Player player;
    private World world;
    private Tree tree;
    private PauseManager pauseManager;

    public GameState(Handler handler) {
        super(handler);
        //PLAYER'S START POSITION INITIALIZING
        world = new World(handler, "./res/World/world.txt");
        handler.setWorld(world);
        handler.getGameCamera().move(0, 0);
        this.pauseManager = handler.getMouseManager().getPauseManager();
    }

    @Override
    public void tick() {
        world.tick();
        super.getHandler().getGameCamera().move(1, 1);
        if(super.getHandler().getGame().getKeyManager().pause){
            State.setState(super.getHandler().getGame().getPauseState());
            super.getHandler().getMouseManager().setPauseManager(pauseManager);
        }
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }

}