package Levels;

import backgrounds.Building;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Green 3.
 */
public class Green3 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {

        List<Velocity> velocities = new ArrayList<>();
        int ballSpeed = 5;
        velocities.add(0, Velocity.fromAngleAndSpeed(315, ballSpeed));
        velocities.add(1, Velocity.fromAngleAndSpeed(45, ballSpeed));

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Building(new Point(100, 200), 100);
    }

    @Override
    public List<Block> blocks() {

        List<Block> blocks = new ArrayList<>();
        int blockWidth = 60;
        int blockHeight = 30;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                // move left
                Color color = null;
                switch (i + 1) {
                    default:
                        color = Color.darkGray;
                    case (1):
                        color = Color.DARK_GRAY;
                        break;
                    case (2):
                        color = Color.RED;
                        break;
                    case (3):
                        color = Color.YELLOW;
                        break;
                    case (4):
                        color = Color.blue;
                        break;
                    case (5):
                        color = Color.WHITE;
                        break;

                }
                double x = 800 - ((j + 1) * blockWidth);
                double y = 100 + i * blockHeight;
                Block b = new Block(new Rectangle(new Point(x, y), blockWidth, blockHeight), color);
                blocks.add(b);
                //  this.blockCount.increase(1);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
