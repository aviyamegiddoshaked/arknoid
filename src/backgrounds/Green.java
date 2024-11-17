package backgrounds;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type Green.
 * green background.
 */
public class Green implements Sprite {
    @Override
    public void drawOn(DrawSurface d) {
        Color green = new Color(0, 100, 0);
        d.setColor(green);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }

    @Override
    public void timePassed() {

    }
}
