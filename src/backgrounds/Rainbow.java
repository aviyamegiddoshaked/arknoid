package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;

import java.awt.Color;


/**
 * The type Rainbow.
 */
public class Rainbow implements Sprite {

    private Point moonCenter;
    private int moonSize;

    /**
     * Instantiates a new Rainbow.
     *
     * @param moonCenter the moon center
     * @param moonSize   the moon size
     */
    public Rainbow(Point moonCenter, int moonSize) {
        this.moonCenter = moonCenter;
        this.moonSize = moonSize;
    }


    @Override
    public void drawOn(DrawSurface d) {

        // just blank backround
        Color sunset = new Color(0, 102, 204);
        d.setColor(sunset);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // cloud 1
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(650, 400, 40);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(580, 390, 40);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(620, 405, 30);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(615, 407, 20);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(580, 380, 40);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(550, 420, 30);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(600, 440, 30);
        d.setColor(Color.LIGHT_GRAY);
        d.fillCircle(655, 440, 30);

        // moon
        d.setColor(Color.white);
        d.fillCircle((int) this.moonCenter.getX(), (int) this.moonCenter.getY(), moonSize);

        // second cloud
        Color lightpink = new Color(255, 204, 204);
        d.setColor(lightpink);
        d.fillCircle(120, 400, 30);
        d.setColor(lightpink);
        d.fillCircle(150, 400, 35);
        d.setColor(lightpink);
        d.fillCircle(190, 400, 30);
        d.setColor(lightpink);
        d.fillCircle(115, 425, 30);
        d.setColor(lightpink);
        d.fillCircle(155, 435, 35);
        d.setColor(lightpink);
        d.fillCircle(190, 425, 30);


        // cloud 3
        Color pink = new Color(222, 165, 164);
        d.setColor(pink);
        d.fillCircle(350, 270, 20);
        d.setColor(pink);
        d.fillCircle(380, 275, 20);
        d.setColor(pink);
        d.fillCircle(365, 300, 20);
        d.setColor(pink);
        d.fillCircle(330, 295, 20);
        d.setColor(pink);
        d.fillCircle(400, 300, 20);


        // cloud 4
        Color orange = new Color(220, 160, 0);

        d.setColor(orange);
        d.fillCircle(500, 120, 20);
        d.setColor(orange);
        d.fillCircle(530, 125, 20);
        d.setColor(orange);
        d.fillCircle(515, 150, 20);
        d.setColor(orange);
        d.fillCircle(480, 145, 20);
        d.setColor(orange);
        d.fillCircle(550, 150, 20);

    }


    @Override
    public void timePassed() {

    }
}













