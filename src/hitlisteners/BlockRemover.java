package hitlisteners;

import interfaces.HitListener;
import setting.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * The type Block remover.
 * in charge of removing blocks that are hit by the ball.
 */
// a BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
    public class BlockRemover implements HitListener {
        private GameLevel game;
        private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     * Aim: blocks that are hit will be removied from the game.
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
            this.game = game;
            this.remainingBlocks = removedBlocks;
        }
    @Override
        // Blocks that are hit should be removed
        // from the game. Remember to remove this listener from the block
        // that is being removed from the game.
        public void hitEvent(Block beingHit, Ball hitter) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
