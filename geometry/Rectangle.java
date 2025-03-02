//Talya Cohen- 213954217
package geometry;
import java.awt.Color;
import java.util.ArrayList;
import biuoop.DrawSurface;

/**
 * the class maka a triangle and the methods that connected that.
 */
public class Rectangle {
    //fields
    private Point upperLeft;
    private double width;
    private double height;
    private final Color color;
    private Line[] lines;

    /**
     * Constructs a rectangle with the specified parameters.
     * @param xstart The x-coordinate of the starting point of the rectangle.
     * @param ystart The y-coordinate of the starting point of the rectangle.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     * @param color The color of the rectangle.
     */
    public Rectangle(double xstart, double ystart, double width, double height, Color color) {
        this.upperLeft = new Point((int) xstart, (int) ystart);
        this.width = width;
        this.height = height;
        this.color = color;
        this.lines = new Line[4];
        this.lines[0] = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
        this.lines[1] = new Line(upperLeft.getX(), upperLeft.getY() + height,
                width + upperLeft.getX(), upperLeft.getY() + height);
        this.lines[2] = new Line(width + upperLeft.getX(), upperLeft.getY() + height,
                width + upperLeft.getX(), upperLeft.getY());
        this.lines[3] = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
    }
    /**
     * Gets the width of the rectangle.
     * @return The width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Sets the width of the rectangle.
     * @param width The new width of the rectangle.
     */
    public void setWidth(double width) {
        this.width = width;
    }
    /**
     * Gets the height of the rectangle.
     * @return The height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * Sets the height of the rectangle.
     * @param height The new height of the rectangle.
     */
    public void setHeight(double height) {
        this.height = height;
    }
    /**
     * Gets the starting point of the rectangle.
     * @return The starting point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Sets the upper-left point of the rectangle and update lines.
     * @param upperLeft The new upper-left point of the rectangle.
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
        this.createLineArr();
    }
    /**
     * Gets the color of the rectangle.
     * @return The color of the rectangle.
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * Draws the rectangle on a specified drawing surface.
     * @param surface The drawing surface on which to draw the rectangle.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                (int) this.width, (int) this.height);
    }

    /**
     * Creates an array of lines representing the edges of the rectangle.
     */
    public void createLineArr() {
        lines = new Line[4];
        lines[0] = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
        lines[1] = new Line(upperLeft.getX(), upperLeft.getY() + height,
                width + upperLeft.getX(), upperLeft.getY() + height);
        lines[2] = new Line(width + upperLeft.getX(), upperLeft.getY() + height,
                width + upperLeft.getX(), upperLeft.getY());
        lines[3] = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
    }
    /**
     * Gets the array of lines representing the edges of the rectangle.
     * @return The array of lines representing the edges of the rectangle.
     */
    public Line[] getLines() {
        this.createLineArr();
        return lines;
    }
    /**
     * Checks if a specified point is inside the rectangle.
     * @param p The point to check.
     * @param radius The radius of the point (for inclusive checking).
     * @return True if the point is inside the rectangle, false otherwise.
     */
    public boolean isInside(Point p, int radius) {
        if (p.getX() - radius < this.upperLeft.getX() + this.width && p.getX() + radius > this.upperLeft.getX()) {
            return p.getY() - radius < this.upperLeft.getY() + this.height && p.getY() + radius > this.upperLeft.getY();
        }
        return false;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     * @param line The line to find intersection points with.
     * @return A List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> points = new ArrayList<>();
        for (int i = 0; i < this.getLines().length; i++) {
            if (line.intersectionWith(this.getLines()[i]) != null) {
                points.add(line.intersectionWith(this.getLines()[i]));
            }
        }
        return points;
    }
}
