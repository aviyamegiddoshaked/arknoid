package geometry;

import java.util.List;

/**
 * The type Line.
 */
public class Line {

    // Setting Variables
    private Point start;
    private Point end;
    private double slope;
    //intersection with y=b
    private double b;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        // Setting Variables
        this.start = start;
        this.end = end;
        this.calculateSlopeAndB();
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        this.calculateSlopeAndB();
    }

    /**
     * This function is used to measure the angle/slope between two points.
     */
    private void calculateSlopeAndB() {
        // to measure slope
        double dy = this.start.getY() - this.end.getY();
        double dx = this.start.getX() - this.end.getX();
        this.slope = dy / dx;

        // if the slope is a straight line from x down , no intersection with y (or on y in a straight line)
        if (dx == 0) {
            this.slope = Double.POSITIVE_INFINITY;
            this.b = Double.POSITIVE_INFINITY;
        } else {
            this.slope = dy / dx;
            this.b = this.start.getY() - this.slope * this.start.getX();
        }
    }


    /**
     * Length double.
     *
     * @return the double
     */
// Return the length of the line
    public double length() {
        return this.start.distance(this.end);
    }


    /**
     * Middle point.
     * Returns the middle point of the line.
     * To find the middle line we will take x coordinates of two points
     * and y coordiantes of same two points, sum them and divide by two
     *
     * @return the point
     */
    public Point middle() {
        double middleX = (this.start.getX() + this.end.getX()) / 2;
        double middleY = (this.start.getY() + this.end.getY()) / 2;

        return new Point(middleX, middleY);
    }

    /**
     * Start point.
     *
     * @return the start point of line
     */
    public Point start() {
        return this.start;
    }

    /**
     * End point.
     *
     * @return the end point of line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return true if lines interesect, false otherwise
     */
// Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        Point inter = this.intersectionWith(other);

        return inter != null;
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the intersection point if the lines intersect, and null otherwise
     */
    public Point intersectionWith(Line other) {
        double db;
        double dm;
        Point inter;

        // line are parallel to y if they don't have mutual points return null
        if (this.slope == Double.POSITIVE_INFINITY && other.slope == Double.POSITIVE_INFINITY) {
            if (this.start.equals(other.start) || this.start.equals(other.end)) {
                return this.start;
            }

            if (this.end.equals(other.start) || this.end.equals(other.end)) {
                return this.end;
            }
            return null;
        }

        // if one of them is infinity (no intersection with y or is on y)
        if (this.slope == Double.POSITIVE_INFINITY || other.slope == Double.POSITIVE_INFINITY) {
            if (this.slope == Double.POSITIVE_INFINITY) {
                double xI = this.start.getX();
                // y= m2x+b2
                double yI = other.slope * xI + other.b;
                inter = new Point(xI, yI);
            } else {
                double xI = other.start.getX();
                double yI = this.slope * xI + this.b;
                inter = new Point(xI, yI);
            }
        } else {
            db = this.b - other.b;
            dm = other.slope - this.slope;
            if (dm == 0) {
                if (this.start.equals(other.start) || this.start.equals(other.end)) {
                    return this.start;
                }

                if (this.end.equals(other.start) || this.end.equals(other.end)) {
                    return this.end;
                }
                return null;
            }
            double xI = db / dm;
            double yI = this.slope * xI + this.b;
            inter = new Point(xI, yI);
        }
        if (this.isPointOnLine(inter) && other.isPointOnLine(inter)) {
            return inter;
        }
        return null;
    }

    /**
     * Is point on line boolean.
     *
     * @param p the p
     * @return the boolean
     */
// checking if the point is on the line
    public boolean isPointOnLine(Point p) {
        double xMin;
        double yMin;
        double xMax;
        double yMax;

        xMin = Math.min(this.start.getX(), this.end.getX());
        yMin = Math.min(this.start.getY(), this.end.getY());
        xMax = Math.max(this.start.getX(), this.end.getX());
        yMax = Math.max(this.start.getY(), this.end.getY());

        boolean isXInRange = xMin <= p.getX() && p.getX() <= xMax;
        boolean isYInRange = yMin <= p.getY() && p.getY() <= yMax;

        return isXInRange && isYInRange;
    }

    /**
     * Equals boolean.
     *
     * @param other Line other  Either they are same starting and ending point or opposite
     * (start-end or end-start) but same points all together
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        boolean isSameStartEnd = this.start.equals(other.start) && this.end.equals(other.end);
        boolean isOpposite = this.end.equals(other.start) && this.start.equals(other.end);

        return isSameStartEnd || isOpposite;
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the closest intersection point to the start of the line.
     */
// If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersect = rect.intersectionPoints(this);

        Point minPoint = null;
        double minDist = this.length();

        for (Point p : intersect) {
            double distance = p.distance(this.start);
            if (distance < minDist) {
                minDist = distance;
                minPoint = p;
            }
        }
        return minPoint;
    }
}
