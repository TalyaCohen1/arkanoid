import gameManagement.Game;

/**
 * Ass5Game class is the main class of the game.
 */
public class Ass5Game {
    //fields
    private final Game myGame;

    /**
     * Constructs a new Ass5Game.
     */
    public Ass5Game() {
        myGame = new Game();
    }

    /**
     * Runs the game.
     */
    public void runMyGame() {
        myGame.initialize();
        myGame.run();
    }

    /**
     * Main method.
     * @param args The command line arguments - not used.
     */
    public static void main(String[] args) {
        Ass5Game game = new Ass5Game();
        game.runMyGame();
    }

}
