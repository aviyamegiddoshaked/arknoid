package hitlisteners;
import interfaces.HitListener;
import sprites.Ball;
import sprites.Block;

/**
 * The type Score tracking listener.
 * Tracks score of game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
@Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}