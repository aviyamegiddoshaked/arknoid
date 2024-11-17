import Animations.AnimationRunner;
import Levels.Final4;
import Levels.Green3;
import Levels.HitLevel;
import Levels.WideEasy;
import biuoop.GUI;
import interfaces.LevelInformation;
import setting.GameFlow;
import setting.GameLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 6 game.
 */
public class Ass6Game {

    /**
     * The entry point of application.
     * New game.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Game", GameLevel.WIDTH, GameLevel.HEIGHT);
        AnimationRunner aR = new AnimationRunner(gui, 60);
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new HitLevel());
        levels.add(new WideEasy());
        levels.add(new Green3());
        levels.add(new Final4());

        GameFlow game = new GameFlow(aR, gui.getKeyboardSensor());
        game.runLevels(levels);

    }
}


