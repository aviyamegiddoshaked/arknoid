package sprites;

import biuoop.DrawSurface;
import geometry.Line;
import geometry.Point;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;
import setting.CollisionInfo;
import setting.GameLevel;
import setting.GameEnvironment;

import java.awt.Color;

/**
 * The type Ball.
 */
public class Ball implements Sprite {

    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvio;


    /**
     * Instantiates a new Ball.
     *
     * @param center the center of ball
     * @param r      the radius of ball
     * @param color  the color of ball
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Sets game envio.
     *
     * @param gameEnvio the game envio
     */
    public void setGameEnvio(GameEnvironment gameEnvio) {
        this.gameEnvio = gameEnvio;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size of radius.
     *
     * @return the size of radius
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Gets color of ball.
     *
     * @return the color of ball
     */
    public java.awt.Color getColor() {

        return this.color;
    }

    /**
     * Draw on.
     * will draw the circle on given surface
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
        surface.setColor(this.color);
        // draw circle
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.radius);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Move one step.
     * Method with the aim of moving the ball according to given speed and knowing where to go once hit boundries
     */
    public void moveOneStep() {
        // take the speed and apply it on the center to  move it
        if (this.gameEnvio == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
        // start of line is center of ball and the speed and distance is end
        Line trajectory = new Line(this.center, this.velocity.applyToPoint(this.center));
        CollisionInfo cI = this.gameEnvio.getClosestCollision(trajectory);
        Collidable collide = cI.collisionObject();
        if (collide == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            return;
        }
       this.velocity = collide.hit(this, cI.collisionPoint(), this.velocity);
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Move one step.
     * Method for clearer guidlines on the area in which the ball can move.
     * Once ball reaches end of screen - bounce back.
     *
     * @param start the start
     * @param end   the end
     */
// making sure we stay in screens frame
    public void moveOneStep(Point start, Point end) {
        double minX = start.getX();
        double minY = start.getY();
        double maxX = end.getX();
        double maxY = end.getY();

        // new point where the center will be
        Point n = this.velocity.applyToPoint(this.center);
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        if (n.getX() < minX + this.radius || n.getX() > maxX - this.radius) {
            // changing direction of dx
            dx *= -1;
        }
        if (n.getY() < minY + this.radius || n.getY() > maxY - this.radius) {
            // changing direction of dy
            dy *= -1;
        }
        Velocity v = new Velocity(dx, dy);
        this.center = v.applyToPoint(this.center);
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param v the v
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * Add to game.
     * Adds the paddle to the game.
     * @param g the g
     */
// Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        this.gameEnvio = g.getEnvironment();
    }
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}

