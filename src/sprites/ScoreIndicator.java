package sprites;

import biuoop.DrawSurface;
import hitlisteners.Counter;
import interfaces.Sprite;
import setting.GameLevel;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
        private Counter score;


    /**
     * Instantiates a new Score indicator.
     *
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(GameLevel.WIDTH / 3, 10, GameLevel.WIDTH / 3, 20);
        d.setColor(Color.BLACK);
        d.drawText(GameLevel.WIDTH / 2 -30, 25, "Score:" + String.valueOf(this.score.getValue()), 16);
    }

    @Override
    public void timePassed() {

    }
}
