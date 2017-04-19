package game;

//MAIN METHOD CLASS
public class Launcher {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = WIDTH / 16 * 9;
    private static final String NAME = "The Wandering Knight";

    public static void main(String[] args) {
        Game game = new Game(NAME, WIDTH, HEIGHT);
        game.start();

    }
}
