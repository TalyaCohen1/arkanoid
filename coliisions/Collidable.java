//Talya Cohen- 213954217
package coliisions;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import sprites.Ball;

/**
 * An interface representing an object that can collide with other objects.
 */
public interface Collidable {

    /**
     * Returns the collision shape of the object.
     * @return The collision shape of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that it has been collided with at the specified collision point with a given velocity.
     * @param hitter The ball that hit the object.
     * @param collisionPoint The point at which the collision occurred.
     * @param currentVelocity The current velocity of the colliding object.
     * @return The new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
