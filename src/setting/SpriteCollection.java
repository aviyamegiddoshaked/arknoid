package setting;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 */
public class SpriteCollection {

    private List<Sprite> collection;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.collection = new ArrayList<>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.collection.add(s);
    }

    /**
     * Notify all time passed.
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed() {
        for (Sprite s : new ArrayList<>(this.collection)) {
            s.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : new ArrayList<>(this.collection)) {
            s.drawOn(d);
        }
    }

    /**
     * Remove sprite.
     * Removes sprite s from collection.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.collection.remove(s);
    }
}

