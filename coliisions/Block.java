//Talya Cohen- 213954217
package coliisions;
import biuoop.DrawSurface;
import gameManagement.Game;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;
import sprites.Ball;
import sprites.Sprite;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a block in the game, which is a type of collidable object and a sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //fields
    private final Rectangle bounds;
    private Color boundsColor;
    private List<HitListener> hitListeners;
    /**
     * Constructs a block with the specified rectangle as its bounds.
     * @param bounds The rectangle representing the bounds of the block.
     */
    public Block(Rectangle bounds) {
        this.bounds = bounds;
        this.boundsColor = Color.BLACK;
    }
    /**
     * Constructs a block with the specified position, width, height, and color.
     * @param x The x-coordinate of the upper-left corner of the block.
     * @param y The y-coordinate of the upper-left corner of the block.
     * @param width The width of the block.
     * @param height The height of the block.
     * @param color The color of the block.
     */
    public Block(int x, int y, int width, int height, Color color) {
        this.bounds = new Rectangle(x, y, width, height, color);
        this.boundsColor = Color.BLACK;
    }
    /**
     * Sets the color of the block's bounds.
     * @param color The color to set for the block's bounds.
     */
    public void setBoundsColor(Color color) {
        this.boundsColor = color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return bounds;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line[] line = this.bounds.getLines();
        //lines parallel to Y
        if (line[0].isPointOnLine(collisionPoint, line[0]) || line[2].isPointOnLine(collisionPoint, line[2])) {
            currentVelocity.setDx(-currentVelocity.getDx());
            if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
            }
        }
        //lines parallel to X
        if (line[1].isPointOnLine(collisionPoint, line[1]) || line[3].isPointOnLine(collisionPoint, line[3])) {
            currentVelocity.setDy(-currentVelocity.getDy());
            if (!ballColorMatch(hitter)) {
                this.notifyHit(hitter);
            }
        }

        return currentVelocity;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.bounds.getColor());
        surface.fillRectangle((int) this.bounds.getUpperLeft().getX(), (int) this.bounds.getUpperLeft().getY(),
                (int) this.bounds.getWidth(), (int) this.bounds.getHeight());
        surface.setColor(this.boundsColor);
        surface.drawRectangle((int) this.bounds.getUpperLeft().getX(), (int) this.bounds.getUpperLeft().getY(),
                (int) this.bounds.getWidth(), (int) this.bounds.getHeight());
    }

    @Override
    public void timePassed() {
        // Empty implementation
    }
    /**
     * Adds the block to the specified game, registering it as a collidable and a sprite.
     * @param myGame The game to add the block to.
     */

    public void addToGame(Game myGame) {
        myGame.addCollidable(this);
        myGame.addSprite(this);
    }

    /**
     * Checks if the ball's color matches the block's color.
     * @param ball The ball to check.
     * @return True if the ball's color matches the block's color, false otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor().equals(this.bounds.getColor());
    }

    /**
     * Removes the block from the specified game.
     * @param game The game to remove the block from.
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * Notifies all listeners about a hit event.
     * @param hitter The ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        if (this.hitListeners == null) {
            return;
        }
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    @Override
    public void addHitListener(HitListener hl) {
        if (this.hitListeners == null) {
            this.hitListeners = new ArrayList<>();
        }
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        if (this.hitListeners != null) {
            this.hitListeners.remove(hl);
        }
    }
}
