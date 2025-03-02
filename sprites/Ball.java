//Talya Cohen- 213954217
package sprites;
import biuoop.DrawSurface;
import coliisions.CollisionInfo;
import gameManagement.Game;
import gameManagement.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;

/**
 * Represents a ball in a 2D space.
 */
public class Ball implements Sprite {

    //fields
    private final int r;
    private Point loc;
    private java.awt.Color color;
    private Velocity vel;
    private GameEnvironment myEnvironment;

    /**
     * Constructs a ball with a given center point, radius, and color.
     * @param center The center point of the ball.
     * @param r      The radius of the ball.
     * @param color  The color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.r = r;
        this.loc = center;
        this.color = color;
        this.vel = new Velocity(0, 0);
    }

    /**
     * Constructs a ball with given coordinates, radius, and color.
     * @param x :The x-coordinate of the center of the ball.
     * @param y :The y-coordinate of the center of the ball.
     * @param r :The radius of the ball.
     * @param color :The color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.r = r;
        this.loc = new Point(x, y);
        this.color = color;
        this.vel = new Velocity(0, 0);
    }
    /**
     * Constructs a ball with given coordinates, radius, color, and game environment.
     * @param x :The x-coordinate of the center of the ball.
     * @param y :The y-coordinate of the center of the ball.
     * @param r :The radius of the ball.
     * @param color :The color of the ball.
     * @param env :The game environment of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment env) {
        this.r = r;
        this.loc = new Point(x, y);
        this.color = color;
        this.vel = new Velocity(0, 0);
        this.myEnvironment = env;
    }

    /**
     * Gets the x-coordinate of the center of the ball.
     * @return The x-coordinate of the center of the ball.
     */
    public int getX() {
        if (this.loc == null) {
            return -1;
        }
        return (int) this.loc.getX();
    }

    /**
     * Gets the y-coordinate of the center of the ball.
     * @return The y-coordinate of the center of the ball.
     */
    public int getY() {
        if (this.loc == null) {
            return -1;
        }
        return (int) this.loc.getY();
    }

    /**
     * Gets the radius of the ball.
     * @return The radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * Gets the color of the ball.
     * @return The color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Sets the color of the ball.
     * @param newColor The new color of the ball.
     */
    public void setColor(java.awt.Color newColor) {
        this.color = newColor;
    }

    /**
     * Sets the velocity of the ball.
     * @param v The new velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * Sets the velocity of the ball.
     * @param dx The horizontal component of the new velocity.
     * @param dy The vertical component of the new velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * Gets the velocity of the ball.
     * @return The velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.vel;
    }
    /**
     * Sets the game environment of the ball.
     * @param myEnvironment The game environment of the ball.
     */
    public void setMyEnvironment(GameEnvironment myEnvironment) {
        this.myEnvironment = myEnvironment;
    }

    /**
     * Draws the ball on the given DrawSurface.
     * @param surface The DrawSurface on which to draw the ball.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), r);
    }

    /**
     * Moves the ball one step.
     */
    public void moveOneStep() {
        Point nextLoc = this.vel.applyToPoint(this.loc);
        Line ballMove = new Line(this.loc.getX(), this.loc.getY(), nextLoc.getX(), nextLoc.getY());
        CollisionInfo intersection = myEnvironment.getClosestCollision(ballMove);
        if (intersection != null) {
            Point collisionPoint = intersection.collisionPoint();
            // Adjust the ball's position to just outside the object
            if (this.vel.getDx() >= 0) {
                this.loc.setX(collisionPoint.getX() - this.r);
            } else if (this.vel.getDx() < 0) {
                this.loc.setX(collisionPoint.getX() + this.r);
            }
            if (this.vel.getDy() >= 0) {
                this.loc.setY(collisionPoint.getY() - this.r);
            } else if (this.vel.getDy() < 0) {
                this.loc.setY(collisionPoint.getY() + this.r);
            }
            // Update velocity based on collision
            this.vel = intersection.collisionObject().hit(this, collisionPoint, this.vel);
            if (intersection.isPaddle()
                    && intersection.collisionObject().getCollisionRectangle().isInside(this.loc, this.r)) {
                this.loc.setY(this.loc.getY() - 10);
            }
        } else {
            // No collision, move the ball
            this.loc = this.getVelocity().applyToPoint(this.loc);
        }

    }
    /**
     * Adds the ball to the game.
     * @param myGame The game to add the ball to.
     */
    public void addToGame(Game myGame) {
        myGame.addSprite(this);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }
    /**
     * Removes the ball from the game.
     * @param myGame The game to remove the ball from.
     */
    public void removeFromGame(Game myGame) {
        myGame.removeSprite(this);
    }
}

