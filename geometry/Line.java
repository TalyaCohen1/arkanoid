//Talya Cohen- 213954217
package geometry;


/**
 * Represents a line segment in 2D space defined by two points.
 */
public class Line {
    //fields
    private final Point start;
    private final Point end;
    private Point intersection = null;
    private double length;
    private static final double EPSILON = 0.0000001;

    /**
     * Construct to a geometry.Line object given two points.
     * @param start The starting point of the line segment.
     * @param end   The ending point of the line segment.
     */
    public Line(Point start, Point end) {
        if (start.getX() == end.getX()) {
            if (start.getY() < end.getY()) {
                this.start = start;
                this.end = end;
            } else {
                this.start = end;
                this.end = start;
            }
        } else {
            this.start = start.getX() < end.getX() ? start : end;
            this.end = start.getX() < end.getX() ? end : start;
        }
    }

    /**
     * Constructs a geometry.Line object given coordinates of two points.
     * @param x1 The x-coordinate of the starting point.
     * @param y1 The y-coordinate of the starting point.
     * @param x2 The x-coordinate of the ending point.
     * @param y2 The y-coordinate of the ending point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }
    /**
     * Calculates the length of the line.
     * @return The length of the line
     */
    public double length() {
        return this.length;
    }
    /**
     * Calculates the midpoint of the line.
     * @return The midpoint of the line.
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2.0;
        double midY = (start.getY() + end.getY()) / 2.0;
        return new Point(midX, midY);
    }

    /**
     * Retrieves the starting point of the line.
     * @return The starting point of the line.
     */
    public Point start() {
        return this.start;
    }
    /**
     * Retrieves the ending point of the line.
     * @return The ending point of the line.
     */
    public Point end() {
        return this.end;
    }
    /**
     * Checks if the line sis parallel to the Y-axis.
     * @return True if line is parallel to the Y-axis, false otherwise.
     */
    public boolean isLineParallelToY() {
        return Math.abs(this.end.getX() - this.start.getX()) < EPSILON;
    }

    /**
     * Checks if the line  is parallel to the X-axis.
     * @return True if line  is parallel to the X-axis, false otherwise.
     */
    public boolean isLineParallelToX() {
        return Math.abs(this.end.getY() - this.start.getY()) < EPSILON;
    }

    /**
     * Calculates the slope of the line segment defined by two points.
     * @param p1 The starting point of the line segment.
     * @param p2 The ending point of the line segment.
     * @return The slope of the line segment.
     */
    private double calculateSlope(Point p1, Point p2) {
        if (Math.abs(p2.getX() - p1.getX()) < EPSILON) {
            return Double.POSITIVE_INFINITY; // Vertical line
        }
        return (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
    }

    /**
     * Calculates the constant of the line equation (y = mx + c) given a point and slope.
     * @param point The point on the line.
     * @param slope The slope of the line.
     * @return The constant of the line equation.
     */
    private double calculateConstant(Point point, double slope) {
        return point.getY() - (slope * point.getX());
    }

    /**
     * check if given pont is on given line.
     * @param p the point we want to check.
     * @param other the line we check if the point on him.
     * @return true if pont is on the line and false otherwise.
     */
    public boolean isPointOnLine(Point p, Line other) {
        if (other.isLineParallelToY()) {
            if (p.getX() == other.start.getX()) {
                return !(p.getY() + EPSILON < other.start.getY()) && !(p.getY() - EPSILON > other.end.getY());
            }
        }
        double slope = calculateSlope(other.start, other.end);
        double constant = calculateConstant(other.start, slope);
        //check if the point is on the line
        if (Math.abs(p.getY() - constant - (slope * p.getX())) < EPSILON) {
            //check ip point is on range.
            if (p.getX() + EPSILON < other.start.getX() || p.getX() - EPSILON > other.end.getX()) {
                return false;
            }
            this.intersection = new Point(p.getX(), p.getY());
            return true;
        }
        return false;
    }

    /**
     * Checks if this line intersects with another line and update the intersection point.
     * @param other The other line to check intersection with.
     * @return True if this line intersects with the other line segment, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //calculate all we need to the equation
        double thisSlope = calculateSlope(this.start, this.end);
        double thisConstant = calculateConstant(this.start, thisSlope);
        double otherSlope = calculateSlope(other.start, other.end);
        double otherConstant = calculateConstant(other.start, otherSlope);
        double yIntersection, xIntersection;

        //if both is a point.
        if (this.start.equals(this.end) && other.start.equals(other.end)) {
            if (this.start.equals(other.start)) {
                intersection = new Point(this.start.getX(), this.start.getY());
                return true;
            }
            return false;
        }
        //just this line is a point
        if (this.start.equals(this.end)) {
            if (otherSlope == Double.POSITIVE_INFINITY) { //parallel to y
                //it can be intersection
                if (Math.abs(this.start.getX() - other.start.getX()) > EPSILON
                        || this.start.getY() - other.end.getY() > EPSILON
                        || EPSILON < other.start.getY() - this.start.getY()) { //check range
                    return false;
                }
                intersection = new Point(this.start.getX(), this.start.getY());
                return true;
            }
            //check if point on the line.
            return this.isPointOnLine(this.start, other);
        }


        //just other line is a point
        if (other.start.equals(other.end)) {
            if (thisSlope == Double.POSITIVE_INFINITY) { //parallel to y
                if (Math.abs(other.start.getX() - this.start.getX()) > EPSILON
                        || other.start.getY() - EPSILON > this.end.getY()
                        || other.start.getY() + EPSILON < this.start.getY()) {
                    return false;
                }
                intersection = new Point(other.start.getX(), other.start.getY());
                return true;
            }
            //check if point on the line.
            return this.isPointOnLine(other.start, this);
        }

        //both line parallel y
        if (thisSlope == Double.POSITIVE_INFINITY && otherSlope == Double.POSITIVE_INFINITY) {
            if (Math.abs(this.start.getX() - other.end.getX()) > EPSILON) {
                return false;
            }
            if (EPSILON < other.start.getY() - this.end.getY() || this.start.getY() - other.end.getY() > EPSILON) {
                return false;
            }
            if (Math.abs(this.start.getY() - other.end.getY()) < EPSILON) {
                intersection = new Point(this.start.getX(), this.start.getY());
            }
            if (Math.abs(this.end.getY() - other.start.getY()) < EPSILON) {
                intersection = new Point(this.end.getX(), this.end.getY());
            }
            return true;
        }

        //just this line is parallel to y
        if (thisSlope == Double.POSITIVE_INFINITY) {
            if (EPSILON < other.start.getX() - this.start.getX()  || this.start.getX() - other.end.getX() > EPSILON) {
                return false;
            }
            yIntersection = otherSlope * this.start.getX() + otherConstant;
            if (yIntersection - EPSILON > this.end.getY() || yIntersection + EPSILON < this.start.getY()) {
                return false;
            }
            intersection = new Point(this.start.getX(), yIntersection);
            return true;
        }
        //just other line is parallel to y.
        if (otherSlope == Double.POSITIVE_INFINITY) {
            if (other.start.getX() - EPSILON > this.end.getX() || other.start.getX() + EPSILON < this.start.getX()) {
                return false;
            }
            yIntersection = thisSlope * other.start.getX() + thisConstant;
            if (yIntersection - EPSILON > other.end.getY() || yIntersection + EPSILON < other.start.getY()) {
                return false;
            }
            intersection = new Point(other.start.getX(), yIntersection);
            return true;
        }

        //classic situation.
        if (Math.abs(thisSlope - otherSlope) < EPSILON) {
            //if they parallel
            if (Math.abs(thisConstant - otherConstant) > EPSILON) {
                return false;
            }
            //if not in rage
            if (this.start.getX() - EPSILON > other.end.getX() || this.end.getX() + EPSILON < other.start.getX()) {
                return false;
            }
            if (Math.abs(this.start.getX() - other.end.getX()) < EPSILON) {
                intersection = this.start;
                return true;
            }
            if (Math.abs(this.end.getX() - other.start.getX()) < EPSILON) {
                intersection = this.end;
                return true;
            }
            return true;
        }
        xIntersection = (thisConstant - otherConstant) / (otherSlope - thisSlope);
        yIntersection = (thisSlope * xIntersection) + thisConstant;
        Point myPoint = new Point(xIntersection, yIntersection);
        if (this.isPointOnLine(myPoint, this) && this.isPointOnLine(myPoint, other)) {
            intersection = new Point(myPoint.getX(), myPoint.getY());
            return true;
        }
        return false;
    }

    /**
     * Check if this line intersects with both other lines.
     * @param other1 one line we check if our intersected with
     * @param other2 another line we check if our intersected with
     * @return true if line is intersecting with both lines.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }


    /**
     * Calculates the intersection point with another line and reset the intersection point.
     * @param other The other line segment to find intersection with.
     * @return The intersection point if it exists, null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            if (this.intersection != null) {
                Point intersection = new Point(this.intersection.getX(), this.intersection.getY());
                this.intersection = null; // Clear the intersection point for the next call
                return intersection;
            }
        }
        return null;
    }

    /**
     * Checks if this line is equal to another line.
     * @param other The other lineto compare with.
     * @return True if the two line are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (this == null || other == null) {
            return false;
        }
        return start.equals(other.start) && end.equals(other.end);
    }


    /**
     * Finds the closest intersection point to the start of this line segment with the specified rectangle.
     * If this line does not intersect with the rectangle, returns null.
     * @param rect The rectangle to check for intersection with.
     * @return The closest intersection point to the start of this line segment, or null if there is no intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point closestPoint = intersectionPoints.get(0);
        double minDistance = start.distance(closestPoint);
        for (int i = 1; i < intersectionPoints.size(); i++) {
            Point currentPoint = intersectionPoints.get(i);
            double distance = start.distance(currentPoint);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = currentPoint;
            }
        }
        return closestPoint;
    }
}