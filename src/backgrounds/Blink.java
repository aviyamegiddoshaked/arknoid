package backgrounds;

import biuoop.DrawSurface;
import geometry.Point;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The type Blink.
 */
public class Blink implements Sprite {
    /**
     * The Center.
     */
    private Point center;
    /**
     * The Size.
     */
    private int size;
    /**
     * The Factor grow.
     */
    private int factorGrow;
    /**
     * The Color.
     */
    private Color color;
    /**
     * The Frames.
     */
    private int frames;

    /**
     * Instantiates a new Blink.
     *
     * @param blinkCenter the blink center
     * @param size        the size
     * @param color       the color
     */
    public Blink(Point blinkCenter, int size, Color color) {
        this.center = blinkCenter;
        this.size = size;
        this.color = color;
        this.factorGrow = 1;
        this.frames = 0;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Draw on.
     *
     * @param d the d
     */
    @Override
    public void drawOn(DrawSurface d) {
        int xCenter = (int) this.center.getX();
        int yCenter = (int) this.center.getY();
        d.setColor(color);
        if (frames < 10) {
            d.fillCircle(xCenter, yCenter, size);
        }
        d.drawCircle(xCenter, yCenter, size);
    }

    /**
     * Time passed.
     */
    @Override
    public void timePassed() {
        this.size += this.factorGrow;
        if (color.getBlue() > 100 && color.getGreen() > 100) {
            this.color = new Color(color.getRed(), color.getGreen() - 1, color.getBlue() - 1);
        }
        this.frames++;
    }
}
