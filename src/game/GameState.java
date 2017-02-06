package game;

import entity.Player;
import tiles.Tile;
import world.World;

import java.awt.*;

//GAME STATE, WILL GET USEFUL IF WE HAVE OTHER STATES LIKE MENU STATE, RENDER LOGIC GOES HERE
public class GameState extends State {

    private Player player;
    private World world;

    public GameState(Game game) {
        super(game);
        //PLAYER'S START POSITION INITIALIZING
        player = new Player(game, 10, 10);
        world = new World(game, "./res/World/world.txt");

        game.getGameCamera().move(0, 0);
    }

    @Override
    public void tick() {
        world.update();
        player.tick();
        game.getGameCamera().move(1, 1);
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
        Tile.tiles[0].render(g, 0, 0);
    }
}
