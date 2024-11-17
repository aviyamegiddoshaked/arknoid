package Animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * The type End screen.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private String text;

    /**
     * Instantiates a new End screen.
     *
     * @param k     the k
     * @param score the score
     * @param isWon the is won
     */
    public EndScreen(KeyboardSensor k, int score, boolean isWon) {
        this.keyboard = k;
        this.stop = false;
        String won;
        if (isWon) {
            won = "You Win! ";
        } else {
            won = "Game Over. ";
        }
        String scoreText = String.valueOf(score);
        String addText = "Your score is ";
        this.text = won + addText + scoreText;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, this.text, 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }
@Override
    public boolean shouldStop() {
        return this.stop;
    }
}



