package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;
import setting.GameLevel;

import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private Color color;
    private Rectangle rectangle;
    private int speed;

    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard  the keyboard
     * @param color     the color
     * @param rectangle the rectangle
     * @param speed     the speed
     */
    public Paddle(KeyboardSensor keyboard, Color color, Rectangle rectangle, int speed) {
        this.keyboard = keyboard;
        this.color = color;
        this.rectangle = rectangle;
        this.speed = speed;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        // Point upperLeft, double width, double height

        Point point = this.rectangle.getUpperLeft();
        double width = this.rectangle.getWidth();
        double height = this.rectangle.getHeight();

        Point newPoint = new Point(point.getX() - speed, point.getY());
        this.rectangle = new Rectangle(newPoint, width, height);
    }

    /**
     * Move right.
     */
    public void moveRight() {
        Point point = this.rectangle.getUpperLeft();
        double width = this.rectangle.getWidth();
        double height = this.rectangle.getHeight();

        Point newPoint = new Point(point.getX() + speed, point.getY());
        this.rectangle = new Rectangle(newPoint, width, height);
    }

    /**
     * Time passed.
     */
    // Sprite
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY) && this.rectangle.getUpperLeft().getX() >= 0) {
            this.moveLeft();
        }

        // change 800
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY) && this.rectangle.getUpperLeft().getX()
                + rectangle.getWidth() < GameLevel.WIDTH) {
            this.moveRight();
        }
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }


    /**
     * Collidable.
     * @return the rectangle
     */
    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Hit velocity.
     *
     *
     * @param hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     *
     * @return the velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // array of lines of rectangle
        Line[]lines = this.rectangle.getLines();
        // funny paddle
        if (lines[0].isPointOnLine(collisionPoint)) {
            return funnyPaddle(lines[0], collisionPoint, currentVelocity);
        }
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

      for (int i = 1; i < lines.length; i++) {
            if (lines[i].isPointOnLine(collisionPoint)) {
                if (i % 2 == 0) {
                    dy = dy * -1;
                } else {
                    dx = dx * -1;
                }
            }
        }
        // if either changes a ball bounced and hit
        return new Velocity(dx, dy);
    }



    /**
     * Funny Paddle.
     *
     * @param collisionPoint, the collision point.
     * @param currentVelocity, the current velocity.
     * @return the velocity of every regent of paddle.
     */
    private Velocity funnyPaddle(Line line, Point collisionPoint, Velocity currentVelocity) {
        int regent = findRegent(line, collisionPoint);
        if (regent == 3) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        if (regent == -1) {
            regent = 5;
        }
        // angle for every regent modulu 360 so its 360 angle
        int angle = (270 + regent * 30) % 360;
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double speed = Math.sqrt(dx * dx + dy * dy);

        return Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * findRegent.
     *
     * @param line, collisionPoint.
     *
     * @return the regent where ball collides.
     */
    private int findRegent(Line line, Point collisionPoint) {
        double lineSegment = line.length() / 5;

        double pX = collisionPoint.getX();
        double lX = Math.min(line.start().getX(), line.end().getX());

        for (int i = 1; i <= 5; i++) {
            if (pX <= lX + lineSegment * i) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Add the paddle to game.
     *
     * @param g the g
     */
// Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}