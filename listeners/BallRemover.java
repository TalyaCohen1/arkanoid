//Talya Cohen- 213954217
package listeners;
import coliisions.Block;
import gameManagement.Game;
import sprites.Ball;

/**
 * A listeners.BallRemover class.
 */
public class BallRemover implements HitListener {
    //fields
    private final Counter remainingBalls;
    private final Game game;


    /**
     * Constructs a listeners.BallRemover object with the specified game level and counter.
     * @param game The game .
     * @param remainingBalls The counter of remaining balls in the game.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        remainingBalls.decrease(1);
        hitter.removeFromGame(game);
    }
}
