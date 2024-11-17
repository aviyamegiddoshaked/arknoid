package setting;

import geometry.Point;
import interfaces.Collidable;

/**
 * The type Collision info.
 */
public class CollisionInfo {

    // where did i hit and with who
    private Collidable col;
    private Point p;

    /**
     * Instantiates a new Collision info.
     *
     * @param col the col
     * @param p   the p
     */
    public CollisionInfo(Collidable col, Point p) {
        this.col = col;
        this.p = p;
    }

    /**
     * Collision point point.
     *
     * @return the point
     */
// the point at which the collision occurs.
    public Point collisionPoint() {
        return this.p;
    }

    /**
     * Collision object collidable.
     * the collidable object involved in the collision.
     *
     * @return the collidable
     */
// the collidable object involved in the collision.
    public Collidable collisionObject() {
        return this.col;
    }


}

