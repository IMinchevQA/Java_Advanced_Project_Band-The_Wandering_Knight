package game;

import entity.Player;
import tiles.Tile;
import world.World;

import java.awt.*;

//GAME STATE, WILL GET USEFULL IF WE HAVE OTHER STATES LIKE MENU STATE, RENDER LOGIC GOES HERE
public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Game game) {
        super(game);
        player = new Player(game, 100, 100);
        world = new World("./res/World/world.txt");
    }

    @Override
    public void tick() {
        world.update();
        player.tick();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
        Tile.tiles[0].render(g, 0, 0);
    }
}
