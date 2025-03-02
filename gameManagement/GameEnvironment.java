//Talya Cohen- 213954217
package gameManagement;

import coliisions.Collidable;
import coliisions.CollisionInfo;
import geometry.Line;
import geometry.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the environment in which the game takes place.
 */
public class GameEnvironment {
    //fields
    private final List<Collidable> gameBlock;

    /**
     * Constructs a new gameManagement.GameEnvironment.
     */
    public GameEnvironment() {
        this.gameBlock = new ArrayList<>();
    }
    /**
     * Constructs a new gameManagement.GameEnvironment with the specified collidable objects.
     * @param gameBlock The collidable objects in the game.
     */
    public GameEnvironment(List<Collidable> gameBlock) {
        this.gameBlock = new ArrayList<>(gameBlock);
    }
    /**
     * Adds the given collidable object to the environment.
     * @param c The collidable object to add.
     */
    public void addCollidable(Collidable c) {
        this.gameBlock.add(c);
    }

    /**
     * Returns the collidable objects in the game.
     * @return The collidable objects in the game.
     */
    public List<Collidable> getGameBlock() {
        return this.gameBlock;
    }


    /**
     * Determines the closest collision that is going to occur for an object moving from line.start() to line.end().
     * If the object will not collide with any of the collidables in this collection, returns null.
     * @param trajectory Represent the ball movement.
     * @return Information about the closest collision that is going to occur, or null if no collision will occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo collisionInfo = null;
        Point closestPoint = null;
        Point currentPoint = null;
        double closestDistance = Double.MAX_VALUE;
        for (int i = 0; i < gameBlock.size(); i++) {
            currentPoint = trajectory.closestIntersectionToStartOfLine(gameBlock.get(i).getCollisionRectangle());
            if (currentPoint != null) {
                if (currentPoint.distance(trajectory.start()) <= closestDistance) {
                    closestDistance = currentPoint.distance(trajectory.start());
                    closestPoint = currentPoint;
                    collisionInfo = new CollisionInfo();
                    collisionInfo.setCollisionPoint(closestPoint);
                    collisionInfo.setCollisionObject(gameBlock.get(i));
                }
            }
        }
        return collisionInfo;
    }
}
