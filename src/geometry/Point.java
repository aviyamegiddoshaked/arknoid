package geometry;

/**
 * The type Point.
 */
public class Point {

    // Setting Variables
    private double x;
    private double y;

    /**
     * Instantiates a new Point.
     *
     * @param x the x
     * @param y the y
     */
// constructor
    public Point(double x, double y) {
        // Setting Variables
        // made a class of x and y who are double
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other point
     * @return the distance
     */
// distance -- return the distance of this point to the other point
    public double distance(Point other) {
        double disx = this.x - other.getX();
        double disy = this.y - other.getY();

        //To square the distance between dx and dy
        double disxsq = Math.pow(disx, 2);
        double disysq = Math.pow(disy, 2);
        // To find root of dxsq and dysq
        double distance = Math.sqrt(disxsq + disysq);

        return distance;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        boolean isXSame;
        boolean isYSame;

        isXSame = this.x == other.getX();
        isYSame = this.y == other.getY();

        return isXSame && isYSame;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// Return the x and y values of this point
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return this.y;
    }
}
