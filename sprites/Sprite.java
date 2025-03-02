//Talya Cohen- 213954217
package sprites;
import biuoop.DrawSurface;

/**
 * The sprites.Sprite interface represents an object that can be drawn on a DrawSurface and can respond to time passing.
 */
public interface Sprite {

    /**
     * Draw the sprite on the given DrawSurface.
     * @param d The DrawSurface on which to draw the sprite.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that a unit of time has passed.
     */
    void timePassed();
}
