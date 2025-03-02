//Talya Cohen- 213954217
package geometry;

/**
 * Represents a velocity in 2D space.
 */
public class Velocity {

    //fields
    private double dx;
    private double dy;

    /**
     * Constructs a velocity with given horizontal and vertical components.
     * @param dx The horizontal component of the velocity.
     * @param dy The vertical component of the velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Gets the horizontal component of the velocity.
     * @return The horizontal component of the velocity.
     */
    public double getDx() {
        return dx;
    }
    /**
     * Sets the horizontal component of the velocity.
     * @param dx The new horizontal component of the velocity.
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * Gets the vertical component of the velocity.
     * @return The vertical component of the velocity.
     */
    public double getDy() {
        return dy;
    }
    /**
     * Sets the vertical component of the velocity.
     * @param dy The new vertical component of the velocity.
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
    /**
     * Applies this velocity to a point, returning a new point.
     * @param p The point to which this velocity is applied.
     * @return A new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Constructs a velocity from an angle and speed.
     * @param angle The angle  at which the velocity is directed.
     * @param speed The magnitude of the velocity.
     * @return A new velocity with the specified angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double radians = Math.toRadians(angle) - (Math.PI / 2);
        double dx = Math.cos(radians) * speed;
        double dy = Math.sin(radians) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Calculates the speed from a given velocity.
     * @param v The velocity object containing the velocity components.
     * @return The speed calculated from the given velocity.
     */
    public static double getSpeedFromVelocity(Velocity v) {
        return Math.sqrt(Math.pow(v.getDx(), 2) + Math.pow(v.getDy(), 2));
    }

}
