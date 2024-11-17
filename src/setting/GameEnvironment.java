package setting;

import geometry.Line;
import geometry.Point;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game environment.
 */
public class GameEnvironment {

    private List<Collidable> col;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.col = new ArrayList<>();
    }


    /**
     * Adds given collidable to the environment.
     *
     * @param c the c
     */
// add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.col.add(c);
    }

    /**
     * Get closest collision.
     *
     * @param trajectory the trajectory of ball.
     * @return the collision info
     */
// Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    // name of line is trajectory
    public CollisionInfo getClosestCollision(Line trajectory) {

        Collidable minColid = null;
        Point minPoint = null;
        double minDist = trajectory.length();

        // closest intersection on this rectangle
        for (Collidable c : new ArrayList<>(this.col)) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p == null) {
                continue;
            }

            double distance = trajectory.start().distance(p);
            if (distance < minDist) {
                minColid = c;
                minPoint = p;
                minDist = distance;
            }
        }
        return new CollisionInfo(minColid, minPoint);
    }

    /**
     * Remove collidable.
     * Removes collidable c from collection.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.col.remove(c);
    }
}

