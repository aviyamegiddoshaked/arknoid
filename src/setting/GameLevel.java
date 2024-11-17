package setting;

import Animations.AnimationRunner;
import Animations.CountdownAnimation;
import Animations.KeyPressStoppableAnimation;
import Animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import hitlisteners.BallRemover;
import hitlisteners.BlockRemover;
import hitlisteners.Counter;
import hitlisteners.ScoreTrackingListener;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.HitListener;
import interfaces.LevelInformation;
import interfaces.Sprite;
import sprites.Ball;
import sprites.Block;
import sprites.LevelIndicator;
import sprites.LivesIndicator;
import sprites.Paddle;
import sprites.ScoreIndicator;

import java.awt.Color;

/**
 * The type Game.
 */
public class GameLevel implements Animation {
    private KeyboardSensor keyboard;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blockCount;
    private Counter ballCount;
    private Counter score;
    private boolean running;
    private AnimationRunner runner;
    private LevelInformation info;
    private Counter lives;

    /**
     * The constant WIDTH of screen.
     */
    public static final int WIDTH = 800;
    /**
     * The constant HEIGHT of screen.
     */
    public static final int HEIGHT = 600;


    /**
     * Instantiates a new Game.
     *
     * @param kS              the k s
     * @param animationRunner the animation runner
     * @param level           the level
     * @param lives           the lives
     * @param score           the score
     */
    public GameLevel(KeyboardSensor kS, AnimationRunner animationRunner, LevelInformation level, Counter lives,
                     Counter score) {
        this.keyboard = kS;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCount = new Counter();
        this.ballCount = new Counter();
        this.score = score;
        this.running = true;
        this.runner = animationRunner;
        this.info = level;
        this.lives = lives;
    }


    /**
     * Add collidable.
     *
     * @param c the c
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }


    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game.
     * create the Blocks and Ball (and Paddle) and add them to the game.
     */
// Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        this.sprites.addSprite(this.info.getBackground());
        // guards (blocks)
        createGuards();
        // create blocks
        createBlocks();
        // create ball
        createBall();
        //create paddle
        createPedal();
        this.sprites.addSprite(new LevelIndicator(this.info.levelName()));
        this.sprites.addSprite(new LivesIndicator(this.lives));
    }

    /**
     * Create guards.
     */
    public void createGuards() {
        int size = 25;
        Color c = Color.DARK_GRAY;
        BallRemover remover = new BallRemover(this, this.ballCount);


        Block b1 = new Block(new Rectangle(new Point(0, 0), WIDTH, size + 20), c);
        Block b2 = new Block(new Rectangle(new Point(0, HEIGHT - size), WIDTH, size), c);
        Block b3 = new Block(new Rectangle(new Point(0, 0), size, HEIGHT), c);
        Block b4 = new Block(new Rectangle(new Point(WIDTH - size, 0), size, HEIGHT), c);

        b1.addToGame(this);
        b2.addToGame(this);
        b2.addHitListener(remover);
        b3.addToGame(this);
        b4.addToGame(this);
    }

    /**
     * Create balls.
     */
    public void createBall() {
        int numberOfBalls = this.info.numberOfBalls();
        this.ballCount.increase(numberOfBalls);

        for (Velocity velocity : this.info.initialBallVelocities()) {
            Ball b = new Ball(new Point(WIDTH / 2, HEIGHT - 50), 5, Color.WHITE);
            b.setVelocity(velocity);
            b.addToGame(this);
        }


    }

    /**
     * Create pedal.
     */
    public void createPedal() {
        int speed = this.info.paddleSpeed();
        int height = 20;
        int width = this.info.paddleWidth();
        Paddle p = new Paddle(this.keyboard, Color.RED, new Rectangle(
                new Point((WIDTH - width) / 2, HEIGHT - height - 25), width, height), speed);
        p.addToGame(this);
    }

    /**
     * Create blocks.
     */
    public void createBlocks() {
        HitListener sL = new ScoreTrackingListener(this.score);
        this.addSprite(new ScoreIndicator(this.score));
        HitListener remover = new BlockRemover(this, this.blockCount);
        this.blockCount.increase(this.info.numberOfBlocksToRemove());
        for (Block b : this.info.blocks()) {
            b.addToGame(this);
            b.addHitListener(remover);
            b.addHitListener(sL);
        }
    }

    /**
     * Run the game.
     * Start the animation loop.
     */
// Run the game -- start the animation loop.
    public void run() {
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    /**
     * Gets environment.
     *
     * @return the environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Remove collidable.
     *
     * @param c the c
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        // move everyone
        this.sprites.notifyAllTimePassed();
        if (this.ballCount.getValue() == 0) {
            this.lives.decrease(1);
            this.createBall();
            this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        }
        if (this.lives.getValue() == 0) {
            this.running = false;
        }
        if (this.blockCount.getValue() == 0) {
            this.score.increase(100);
            this.running = false; // GAME IS OVER, YOU WON
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new PauseScreen(this.keyboard)));
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
