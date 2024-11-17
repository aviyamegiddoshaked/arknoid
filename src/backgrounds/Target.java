package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Velocity;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type Target.
 */
public class Target implements Sprite {

    private Point center;
    private Point[] points;
    private int degrees;

    /**
     * Instantiates a new Target.
     *
     * @param center the center
     */
    public Target(Point center) {
        this.center = center;
        this.points = new Point[8];
        this.degrees = 0;
        this.timePassed();
    }

    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.center.getX();
        int y = (int) this.center.getY();
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);

        for (int i = 0; i < this.points.length; i++) {
            int x1, y1;
            x1 = (int) this.points[i].getX();
            y1 = (int) this.points[i].getY();
            d.drawLine(x, y, x1, y1);
        }

        for (int i = 1; i <= 3; i++) {
            d.drawCircle(x, y, 30 * i);
        }
    }

    @Override
    public void timePassed() {

        for (int i = 0; i < this.points.length; i++) {
            int d = this.degrees + i * 360 / this.points.length;
            Velocity v = Velocity.fromAngleAndSpeed(d, 100);
            this.points[i] = v.applyToPoint(this.center);
        }
        this.degrees += 5;
    }
}
