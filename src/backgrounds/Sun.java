package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;

import java.awt.Color;


/**
 * The type Sun.
 */
public class Sun implements Sprite {

    private Point sunCenter;
    private int sunSize;

    /**
     * Instantiates a new Sun.
     *
     * @param sunCenter the sun center
     * @param sunSize   the sun size
     */
    public Sun(Point sunCenter, int sunSize) {
        this.sunCenter = sunCenter;
        this.sunSize = sunSize;
    }


    @Override
    public void drawOn(DrawSurface d) {
        // backround
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        int xCenter = (int) this.sunCenter.getX();
        int yCenter = (int) this.sunCenter.getY();

        //the outer layer - the rays of the sun
        d.setColor(new Color(240, 230, 140));
        for (int i = 0; i < 700; i += 10) {
            d.drawLine(xCenter, yCenter, i + 20, (int) this.sunCenter.getY() + 200);
        }


        d.setColor(new Color(236, 170, 0));
        d.fillCircle(xCenter, yCenter, sunSize);
        d.setColor(Color.ORANGE);
        d.fillCircle(xCenter, yCenter, (sunSize * 4) / 5);

        //the Inner layer of the sun
        d.setColor(Color.YELLOW);
        d.fillCircle(xCenter, yCenter, (sunSize * 3) / 5);

    }

    @Override
    public void timePassed() {

    }
}
