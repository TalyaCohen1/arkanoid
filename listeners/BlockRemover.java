//Talya Cohen- 213954217
package listeners;
import coliisions.Block;
import gameManagement.Game;
import sprites.Ball;

/**
 * A listeners.BlockRemover class.
 */
public class BlockRemover implements HitListener {
    //fields
    private final Game game;
    private final Counter remainingBlocks;
    /**
     * Constructs a listeners.BlockRemover object with the specified game level and counter.
     * @param game The game.
     * @param remainingBlocks The counter of remaining blocks in the game.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.remainingBlocks.decrease(1);
    }

}
