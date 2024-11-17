package Levels;

import backgrounds.Sun;
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
 * The type Wide easy.
 */
public class WideEasy implements LevelInformation {
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        int ballSpeed = 5;
        for (int i = 0; i < 11; i++) {
            if (i == 5) {
                continue;
            }
            int angle = (315 + i * 9) % 360;
            velocities.add(Velocity.fromAngleAndSpeed(angle, ballSpeed));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 200;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Sun(new Point(100, 100), 50);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Color[] colours = new Color[] {
                Color.RED, Color.orange, Color.yellow, Color.green, Color.BLUE, Color.pink, Color.CYAN};
        int blockWidth = 800 / 15;
        int blockHeight = 10;
        int x = 25;
        int y = 300;

        for (int i = 0; i < 15; i++) {
            Rectangle r = new Rectangle(new Point(x + blockWidth * i, y), blockWidth, blockHeight);
            Color c = null;
            switch (i) {
                default: c = colours[0];
                case 0:
                case 1:
                    c = colours[0];
                    break;
                case 2:
                case 3:
                    c = colours[1];
                    break;
                case 4:
                case 5:
                    c = colours[2];
                    break;
                case 6:
                case 7:
                case 8:
                    c = colours[3];
                    break;
                case 9:
                case 10:
                    c = colours[4];
                    break;
                case 11:
                case 12:
                    c = colours[5];
                    break;
                case 13:
                case 14:
                    c = colours[6];
                    break;
            }
            blocks.add(new Block(r, c));
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
