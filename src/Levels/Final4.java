package Levels;

import backgrounds.Rainbow;
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
 * The type Final 4.
 */
public class Final4 implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int ballSpeed = 5;
        velocities.add(0, Velocity.fromAngleAndSpeed(330, ballSpeed));
        velocities.add(1, Velocity.fromAngleAndSpeed(0, ballSpeed));
        velocities.add(2, Velocity.fromAngleAndSpeed(30, ballSpeed));
        /*for(int i=0;  i<3; i++){
            int angle = (120 + i * 60) % 360;
            velocities.add(Velocity.fromAngleAndSpeed(angle, ballSpeed));
        }*/
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
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Rainbow(new Point(100, 100), 40);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int blockWidth = 800 / 15;
        int blockHeight = 15;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
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
                    case (6):
                        color = Color.PINK;
                        break;
                    case (7):
                        color = Color.CYAN;
                        break;

                }
                double x = 800 - ((j + 1) * blockWidth);
                double y = 150 + i * blockHeight;
                Block b = new Block(new Rectangle(new Point(x, y), blockWidth, blockHeight), color);
                blocks.add(b);
                //  this.blockCount.increase(1);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
