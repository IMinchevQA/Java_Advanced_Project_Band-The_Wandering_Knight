package game.states;

import entities.creature.Player;
import entities.statics.Tree;
import game.Handler;

import gfx.Assets;
import gfx.ClickListener;
import gfx.UI.PauseManager;
import gfx.UI.UIImageButton;
import gfx.UI.UIManager;
import music.Sound;
import world.World;

import java.awt.*;

//GAME STATE, WILL GET USEFUL IF WE HAVE OTHER STATES LIKE MENU STATE, RENDER LOGIC GOES HERE
public class GameState extends State {

    private Player player;
    private World world;
    private Tree tree;


    public GameState(Handler handler) {
        super(handler);
        //PLAYER'S START POSITION INITIALIZING
        world = new World(handler, "./res/World/world.txt");
        handler.setWorld(world);
        handler.getGameCamera().move(0, 0);
    }

    @Override
    public void tick() {
        world.tick();
        handler.getGameCamera().move(1, 1);
        if(handler.getGame().getKeyManager().pause){
            State.setState(handler.getGame().pauseState);
        }

    }

    @Override
    public void render(Graphics g) {
        world.render(g);

    }

}