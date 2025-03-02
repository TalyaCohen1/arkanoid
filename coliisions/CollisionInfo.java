//Talya Cohen- 213954217
package coliisions;

import geometry.Point;

/**
 * coliisions.CollisionInfo class.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * Constructs a coliisions.CollisionInfo object with no specified collision point and object.
     */
    public CollisionInfo() {
        this.collisionPoint = null;
        this.collisionObject = null;
    }

    /**
     * Sets the collision point.
     * @param point The point we want to update.
     */
    public void setCollisionPoint(Point point) {
        this.collisionPoint = point;
    }
    /**
     * Sets the collision object.
     * @param object The object we want to update.
     */
    public void setCollisionObject(Collidable object) {
        this.collisionObject = object;
    }

    /**
     * check if collision object is a block.
     * @return true if the collision object is a block, false otherwise.
     */
    public boolean isPaddle() {
        return collisionObject instanceof Paddle;
    }

    /**
     * Returns the collision point.
     * @return collision point.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }
    /**
     * Returns the collision object.
     * @return collision object.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}
