//Talya Cohen- 213954217
package sprites;
import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * Represent collection of sprites.
 */
public class SpriteCollection {
    //fields
    private final List<Sprite> sprites;
    /**
     * Constructs a new sprites.SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Gets the list of sprites.
     * @return The list of sprites.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }
    /**
     * Adds a sprite to the collection.
     * @param s The sprite  we want to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }
    /**
     * Calls the timePassed() method on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s: spritesCopy) {
            s.timePassed();
        }
     }

    /**
     * Calls the drawOn method on all sprites.
     * @param d The DrawSurface on which to draw the sprites.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> spritesCopy = new ArrayList<>(this.sprites);
        for (Sprite s: spritesCopy) {
            s.drawOn(d);
        }
    }
}
