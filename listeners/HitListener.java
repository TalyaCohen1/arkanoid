//Talya Cohen- 213954217
package listeners;
import coliisions.Block;
import sprites.Ball;

/**
 * listeners.HitListener interface.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit The block that was hit.
     * @param hitter The ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
