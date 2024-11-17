package interfaces;

import biuoop.DrawSurface;

/**
 * The interface Sprite.
 */
public interface Sprite {
        /**
         * Draw on.
         * Draws the sprite on to the screen.
         *
         * @param d the d
         */
// draw the sprite to the screen
        void drawOn(DrawSurface d);

        /**
         * Time passed.
         */
// notify the sprite that time has passed
        void timePassed();
}
