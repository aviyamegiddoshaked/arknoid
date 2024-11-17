package hitlisteners;


import interfaces.HitListener;
import setting.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * The type Ball remover.
 * class's job: removing balls, and updating an availabe-balls counter when hit.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter counter;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game    the game
     * @param counter the counter
     */
    public BallRemover(GameLevel game, Counter counter) {
        this.game = game;
        this.counter = counter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.counter.decrease(1);
    }
}
