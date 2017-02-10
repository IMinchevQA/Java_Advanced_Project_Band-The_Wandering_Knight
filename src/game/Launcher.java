package game;

//MAIN METHOD CLASS
public class Launcher {

    //Нямам си на идея за какво е това
    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 1000;
    public static final int HEIGHT = WIDTH / 16 * 9;
    public static final String NAME = "The Wandering Knight";

    public Launcher() {


    }

    public static void main(String[] args) {
        Game game = new Game(NAME, WIDTH, HEIGHT);
        game.start();

    }


}
