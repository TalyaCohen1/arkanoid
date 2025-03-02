//Talya Cohen- 213954217
package sprites;
import biuoop.DrawSurface;
import gameManagement.Game;
import listeners.Counter;

import java.awt.Color;
/**
 * The sprites.ScoreIndicator class represents the score indicator in the game.
 */
public class ScoreIndicator implements Sprite {
    //fields
    private final Counter score;

    /**
     * Constructs a sprites.ScoreIndicator object with the specified counter.
     * @param score The counter of the score.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(350, 15, "Score: " + score.getValue(), 15);
    }

    @Override
    public void timePassed() {
        // do nothing
    }

    /**
     * Adds the score indicator to the game.
     * @param g The game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

}
