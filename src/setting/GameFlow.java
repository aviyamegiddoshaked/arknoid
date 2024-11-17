package setting;


import Animations.AnimationRunner;
import Animations.EndScreen;
import biuoop.KeyboardSensor;
import hitlisteners.Counter;
import interfaces.LevelInformation;

import java.util.List;

/**
 * The type Game flow.
 */
public class GameFlow {

    private AnimationRunner aR;
    private KeyboardSensor kS;


    /**
     * Instantiates a new Game flow.
     *
     * @param aR the a r
     * @param kS the k s
     */
    public GameFlow(AnimationRunner aR, KeyboardSensor kS) {
        this.aR = aR;
        this.kS = kS;
    }

    /**
     * Run levels.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter lives = new Counter();
        Counter score = new Counter();
        lives.increase(3);
        boolean gameWon = true;


        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(this.kS, this.aR, levelInfo, lives, score);
            level.initialize();
            level.run();
//            if (lives.getValue() == 0) {
                gameWon = false;
                break;
            }
        }

        EndScreen end = new EndScreen(this.kS, score.getValue(), gameWon);
        this.aR.run(end);

    }
}
