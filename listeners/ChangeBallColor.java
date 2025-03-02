//Talya Cohen- 213954217
package listeners;
import coliisions.Block;
import sprites.Ball;

/**
 * listeners.ChangeBallColor class.
 * This class is in charge of changing the ball's color when it hits a block.
 */
public class ChangeBallColor implements HitListener {
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.setColor(beingHit.getCollisionRectangle().getColor());
    }
}
