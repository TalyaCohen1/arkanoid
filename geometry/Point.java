//Talya Cohen- 213954217
package geometry;
/**
 * Represents a point in an x-coordinate and a y-coordinate.
 */
public class Point {

    //fields
    private double x;
    private double y;
    private static final double EPSILON = 0.0001;

    /**
     * constructor.
     * @param x - The value of the x.
     * @param y -The value of the y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     * @param other -The point from which we want to calculate the distance.
     * @return the distance between the two points
     */
    public double distance(Point other) {
        if (other == null) {
            return 0;
        }
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Checks if the point values are equal.
     * @param other -The point we would like to check
     * @return true if equal and false otherwise
     */
    public boolean equals(Point other) {
        if (other == null || this == null) {
            return false;
        }
        return Math.abs(this.x - other.x) <= EPSILON && Math.abs(this.y - other.y) <= EPSILON;
    }

    /**
     * Returns the x values.
     * @return x value.
     */
    public double getX() {
        return x;
    }
    /**
     * Sets the x-coordinate of this point to the specified value.
     * @param x The new x-coordinate value.
     */

    public void setX(double x) {
        this.x = x;
    }

    /**
     * Returns the y values.
     * @return the y values
     */
    public double getY() {
        return y;
    }
    /**
     * Sets the y-coordinate of this point to the specified value.
     * @param y The new y-coordinate value.
     */
    public void setY(double y) {
        this.y = y;
    }

}
