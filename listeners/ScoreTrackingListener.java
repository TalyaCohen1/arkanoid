//Talya Cohen- 213954217
package listeners;

import coliisions.Block;
import sprites.Ball;

/**
 * A listeners.ScoreTrackingListener class.
 * A listeners.ScoreTrackingListener is in charge of updating the score counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    //fields
    private final Counter currentScore;

    /**
     * Constructs a listeners.ScoreTrackingListener object with the specified counter.
     * @param scoreCounter The counter of the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
    /**
     * Returns the score counter.
     * @return The score counter.
     */
    public Counter getScore() {
        return currentScore;
    }
}
